package com.minibank.creditcard.service.impl;

import com.minibank.creditcard.dto.request.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.UpdateCustomerRequestDTO;
import com.minibank.creditcard.dto.response.CardResponseDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
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
    if (customerRepository.existsByNik(customerDTO.getNik())) {
      throw new DuplicateNIKException(customerDTO.getNik());
    } else if (customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
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
    return null;
  }

  @Override
  public Page<CustomerResponseDTO> getAllActiveCustomers(Pageable pageable) {
    return null;
  }

  @Override
  public CustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO updateRequest) {
    return null;
  }

  @Override
  public void deleteCustomer(Long id) {

  }

  @Override
  public boolean isPhoneNumberTaken(String phoneNumber) {
    return false;
  }

  @Override
  public boolean isNIKTaken(String nik) {
    return false;
  }

  @Override
  public Optional<CustomerResponseDTO> getCustomerByPhoneNumber(String phoneNumber) {
    return Optional.empty();
  }

  @Override
  public CustomerResponseDTO updateKycStatus(Long customerId, Customer.KycStatus status) {
    return null;
  }

  @Override
  public CustomerResponseDTO updateRiskProfile(Long customerId, Customer.RiskProfile profile) {
    return null;
  }

  @Override
  public List<CustomerResponseDTO> searchCustomersByName(String name) {
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

}
