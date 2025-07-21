package com.minibank.creditcard.service.impl;

import com.minibank.creditcard.dto.request.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.UpdateCustomerRequestDTO;
import com.minibank.creditcard.dto.response.CardResponseDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.exception.CustomerNotFoundException;
import com.minibank.creditcard.exception.DuplicateNIKException;
import com.minibank.creditcard.exception.DuplicatePhoneNumberException;
import com.minibank.creditcard.mapper.CustomerMapper;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import com.minibank.creditcard.repository.CardRepository;
import com.minibank.creditcard.repository.CustomerRepository;
import com.minibank.creditcard.service.CustomerService;
import com.minibank.creditcard.util.CardUtil;
import com.minibank.creditcard.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CardRepository cardRepository;

  @Override
  public CustomerResponseDTO registerCustomer(CreateCustomerRequestDTO registrationRequest) {
    var customerDTO = registrationRequest.getCreateCustomerDTO();
    var cardDTO = registrationRequest.getCreateCardDTO();

    // Checks for exception
    if (isNIKTaken(customerDTO.getNik())) {
      throw new DuplicateNIKException(customerDTO.getNik());
    } else if (isPhoneNumberTaken(customerDTO.getPhoneNumber())) {
      throw new DuplicatePhoneNumberException(customerDTO.getPhoneNumber());
    }

    // Building Customer Entity
    Customer customer = Customer.builder()
      .nik(customerDTO.getNik())
      .fullName(customerDTO.getFullName())
      .phoneNumber(customerDTO.getPhoneNumber())
      .address(customerDTO.getAddress())
      .dateOfBirth(customerDTO.getDateOfBirth())
      .kycStatus(Customer.KycStatus.PENDING)
      .riskProfile(Customer.RiskProfile.NOT_SET)
      .build();

    // Building Card Entity
    BigDecimal cardLimit = CardUtil.calculateCardLimit(cardDTO.getMonthlyIncome(), cardDTO.getRequestedLimit());

    Card card = Card.builder()
      .cardNumber(CardUtil.generateCardNumber())
      .cvv(SecurityUtil.hashElement(CardUtil.generateCVV()))
      .pin(SecurityUtil.hashElement(cardDTO.getPin()))
      .cardLimit(cardLimit)
      .availableLimit(cardLimit)
      .issuedDate(LocalDate.now())
      .expiryDate(CardUtil.generateExpiryDate(LocalDate.now()))
      .status(Card.CardStatus.ACTIVE)
      .customer(customer)
      .build();

    customer.setCard(card);
    Customer savedCustomer = customerRepository.save(customer);
    return CustomerMapper.mapCustomerToDTO(savedCustomer);
  }

  @Override
  public CustomerResponseDTO getCustomerById(Long id) {
    return CustomerMapper.mapCustomerToDTO(getCustomerEntityById(id));
  }

  @Override
  public CustomerResponseDTO getCustomerByPhoneNumber(String phoneNumber) {
    Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
      () -> new CustomerNotFoundException("phoneNumber", phoneNumber)
    );
    return CustomerMapper.mapCustomerToDTO(customer);
  }

  @Override
  public CustomerResponseDTO getCustomerByNIK(String nik) {
    Customer customer = customerRepository.findByNik(nik).orElseThrow(
      () -> new CustomerNotFoundException("NIK", nik)
    );
    return CustomerMapper.mapCustomerToDTO(customer);
  }

  @Override
  public Page<CustomerResponseDTO> getAllActiveCustomers(Pageable pageable) {
    return null;
  }

  @Override
  public CustomerResponseDTO updateCustomer(Long customerId, UpdateCustomerRequestDTO updateRequest) {
    Customer customer = getCustomerEntityById(customerId);

    // Check if updated phone number already exists in the database
    if (isPhoneNumberTaken(updateRequest.getPhoneNumber())) {
      throw new DuplicatePhoneNumberException(updateRequest.getPhoneNumber());
    }

    customer.setPhoneNumber(updateRequest.getPhoneNumber());
    customer.setAddress(updateRequest.getAddress());
    customer.setKycStatus(updateRequest.getKycStatus());
    customer.setRiskProfile(updateRequest.getRiskProfile());

    Customer updatedCustomer = customerRepository.save(customer);
    return CustomerMapper.mapCustomerToDTO(updatedCustomer);
  }

  @Override
  public CustomerResponseDTO updateKycStatus(Long customerId, Customer.KycStatus kycStatus) {
    Customer customer = getCustomerEntityById(customerId);
    customer.setKycStatus(kycStatus);

    Customer updatedCustomer = customerRepository.save(customer);
    return CustomerMapper.mapCustomerToDTO(updatedCustomer);
  }

  @Override
  public CustomerResponseDTO updateRiskProfile(Long customerId, Customer.RiskProfile riskProfile) {
    Customer customer = getCustomerEntityById(customerId);
    customer.setRiskProfile(riskProfile);

    Customer updatedCustomer = customerRepository.save(customer);
    return CustomerMapper.mapCustomerToDTO(updatedCustomer);
  }

  @Override
  public List<CustomerResponseDTO> searchCustomersByName(String name) {
    //TODO: To be continue
    List<Customer> customers = customerRepository.findByFullNameContainingIgnoreCase(name);
    return List.of();
  }

  @Override
  public CardResponseDTO getCardByCustomerId(Long customerId) {
    return null;
  }

  @Override
  public CardResponseDTO updateCardStatus(Long customerId, Card.CardStatus newStatus) {

    return null;
  }

  @Override
  public boolean deleteCustomer(Long id) {
    Customer customer = getCustomerEntityById(id);
    customer.getCard().setStatus(Card.CardStatus.DELETED);
    customerRepository.save(customer);
    return true;
  }

  // ------------------------------------------------------------------------------------------
  // ------------------------------------------------------------------------------------------
  // -------------------- -> Private Utility Function <- --------------------------------------
  // ------------------------------------------------------------------------------------------
  // ------------------------------------------------------------------------------------------

  // function to reduce redundancy in fetching customer entity by id
  private Customer getCustomerEntityById(Long customerId) {
    return customerRepository.findById(customerId).orElseThrow(
      () -> new CustomerNotFoundException("id", customerId)
    );
  }

  // check duplicate phone number entry
  private boolean isPhoneNumberTaken(String phoneNumber) {
    return customerRepository.existsByPhoneNumber(phoneNumber);
  }

  // check duplicate NIK entry
  private boolean isNIKTaken(String nik) {
    return customerRepository.existsByNik(nik);
  }

}
