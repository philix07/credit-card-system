package com.minibank.creditcard.service.impl;

import com.minibank.creditcard.dto.request.CreateCustomerRequest;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.mapper.CustomerMapper;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import com.minibank.creditcard.repository.CardRepository;
import com.minibank.creditcard.repository.CustomerRepository;
import com.minibank.creditcard.service.CustomerService;
import com.minibank.creditcard.util.CardUtil;
import com.minibank.creditcard.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CardRepository cardRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public CustomerResponseDTO registerCustomer(CreateCustomerRequest registrationRequest) {
    var customerDTO = registrationRequest.getCreateCustomerDTO();
    var cardDTO = registrationRequest.getCreateCardDTO();

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

}
