package com.minibank.creditcard.service;

import com.minibank.creditcard.dto.request.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.UpdateCustomerRequestDTO;
import com.minibank.creditcard.dto.response.CardResponseDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

  // 1. Register a customer (with card creation)
  CustomerResponseDTO registerCustomer(CreateCustomerRequestDTO registrationRequest);

  // 2. Get customer by ID
  CustomerResponseDTO getCustomerById(Long id);

  // 3. Get all active customers
  Page<CustomerResponseDTO> getAllActiveCustomers(Pageable pageable);

  // 4. Get customer by phone number (used in login or lookup)
  CustomerResponseDTO getCustomerByPhoneNumber(String phoneNumber);

  // 5. Get customer by NIK (used in login or lookup)
  CustomerResponseDTO getCustomerByNIK(String nik);

  // 6. Update customer profile
  CustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO updateRequest);

  // 7. Delete customer (soft delete)
  boolean deleteCustomer(Long id);

  // 8. Update KYC status
  CustomerResponseDTO updateKycStatus(Long customerId, Customer.KycStatus status);

  // 9. Update Risk Profile
  CustomerResponseDTO updateRiskProfile(Long customerId, Customer.RiskProfile profile);

  // 10. Search customers by name (partial match)
  List<CustomerResponseDTO> searchCustomersByName(String name);

  // 11. Get card info for a customer
  CardResponseDTO getCardByCustomerId(Long customerId);

  // 12. Block/activate/update card status
  CardResponseDTO updateCardStatus(Long customerId, Card.CardStatus newStatus);

}
