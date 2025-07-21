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
  public CustomerResponseDTO registerCustomer(CreateCustomerRequestDTO registrationRequest);

  // 2. Get customer by ID
  CustomerResponseDTO getCustomerById(Long id);

  // 3. Get all active customers
  Page<CustomerResponseDTO> getAllActiveCustomers(Pageable pageable);

  // 4. Update customer profile
  CustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO updateRequest);

  // 5. Delete customer (soft delete)
  void deleteCustomer(Long id);

  // 6. Check if phone number is already registered
  boolean isPhoneNumberTaken(String phoneNumber);

  // 7. Check if NIK is already registered
  boolean isNIKTaken(String nik);

  // 8. Get customer by phone number (used in login or lookup)
  Optional<CustomerResponseDTO> getCustomerByPhoneNumber(String phoneNumber);

  // 9. Update KYC status
  CustomerResponseDTO updateKycStatus(Long customerId, Customer.KycStatus status);

  // 10. Update Risk Profile
  CustomerResponseDTO updateRiskProfile(Long customerId, Customer.RiskProfile profile);

  // 11. Search customers by name (partial match)
  List<CustomerResponseDTO> searchCustomersByName(String name);

  // 12. Get card info for a customer
  CardResponseDTO getCardByCustomerId(Long customerId);

  // 13. Block/activate/update card status
  CardResponseDTO updateCardStatus(Long customerId, Card.CardStatus newStatus);

}
