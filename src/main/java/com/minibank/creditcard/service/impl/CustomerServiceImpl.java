package com.minibank.creditcard.service.impl;

import com.minibank.creditcard.dto.request.customer.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.customer.UpdateCustomerRequestDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.exception.CustomerNotFoundException;
import com.minibank.creditcard.exception.DuplicateNIKException;
import com.minibank.creditcard.exception.DuplicatePhoneNumberException;
import com.minibank.creditcard.mapper.CustomerMapper;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import com.minibank.creditcard.repository.CustomerRepository;
import com.minibank.creditcard.service.CustomerService;
import com.minibank.creditcard.util.CardUtil;
import com.minibank.creditcard.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

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
  public Page<CustomerResponseDTO> getCustomersByName(String name, Pageable pageable) {
    Page<Customer> customers = customerRepository.findByFullNameContainingIgnoreCase(name, pageable);
    if (customers.isEmpty()) {
      throw new CustomerNotFoundException("name", name);
    }

    // Mapping Page<Customer> into List<CustomerResponseDTO>
    return customers.map(CustomerMapper::mapCustomerToDTO);
  }

  @Override
  public Page<CustomerResponseDTO> getCustomersByFilter(
    String name,
    Card.CardStatus cardStatus,
    Customer.KycStatus kycStatus,
    Customer.RiskProfile riskProfile,
    Pageable pageable
  ) {

    // We have to create JPA Specs later to enable mixed parameter search

    if (name != null) {
      return customerRepository
        .findByFullNameContainingIgnoreCase(name, pageable)
        .map(CustomerMapper::mapCustomerToDTO);
    } else if (cardStatus != null) {
      return customerRepository
        .findByCardStatus(cardStatus, pageable)
        .map(CustomerMapper::mapCustomerToDTO);
    } else if (kycStatus != null) {
      return customerRepository
        .findByKycStatus(kycStatus, pageable)
        .map(CustomerMapper::mapCustomerToDTO);
    } else if (riskProfile != null) {
      return customerRepository
        .findByRiskProfile(riskProfile, pageable)
        .map(CustomerMapper::mapCustomerToDTO);
    } else {
      return customerRepository
        .findAll(pageable)
        .map(CustomerMapper::mapCustomerToDTO);
    }
  }


  @Override
  public Page<CustomerResponseDTO> getAllActiveCustomers(Pageable pageable) {
    Page<Customer> activeCustomers = customerRepository.findByCardStatus(Card.CardStatus.ACTIVE, pageable);
    return activeCustomers.map(CustomerMapper::mapCustomerToDTO);
  }

  @Override
  public Page<CustomerResponseDTO> getCustomersByCardStatus(Card.CardStatus cardStatus, Pageable pageable) {
    Page<Customer> customerByCardStatus = customerRepository.findByCardStatus(cardStatus, pageable);
    return customerByCardStatus.map(CustomerMapper::mapCustomerToDTO);
  }

  @Override
  public Page<CustomerResponseDTO> getCustomersByKycStatus(Customer.KycStatus kycStatus, Pageable pageable) {
    Page<Customer> customerByKycStatus = customerRepository.findByKycStatus(kycStatus, pageable);
    return customerByKycStatus.map(CustomerMapper::mapCustomerToDTO);
  }

  @Override
  public Page<CustomerResponseDTO> getCustomersByRiskProfile(Customer.RiskProfile riskProfile, Pageable pageable) {
    Page<Customer> customerByRiskProfile = customerRepository.findByRiskProfile(riskProfile, pageable);
    return customerByRiskProfile.map(CustomerMapper::mapCustomerToDTO);
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
