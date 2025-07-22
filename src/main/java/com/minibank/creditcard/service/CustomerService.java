package com.minibank.creditcard.service;

import com.minibank.creditcard.dto.request.customer.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.customer.UpdateCustomerRequestDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

  // Register a customer (with card creation)
  CustomerResponseDTO registerCustomer(CreateCustomerRequestDTO registrationRequest);

  // Get customer by ID
  CustomerResponseDTO getCustomerById(Long id);

  // Get customer by phone number (used in login or lookup)
  CustomerResponseDTO getCustomerByPhoneNumber(String phoneNumber);

  // Get customer by NIK (used in login or lookup)
  CustomerResponseDTO getCustomerByNIK(String nik);

  // Get all active customers
  Page<CustomerResponseDTO> getAllActiveCustomers(Pageable pageable);

  // Get all customer by card status
  Page<CustomerResponseDTO> getCustomersByCardStatus(Card.CardStatus cardStatus, Pageable pageable);

  // Get all customer by kyc status
  Page<CustomerResponseDTO> getCustomersByKycStatus(Customer.KycStatus kycStatus, Pageable pageable);

  // Get all customer by risk profile
  Page<CustomerResponseDTO> getCustomersByRiskProfile(Customer.RiskProfile riskProfile, Pageable pageable);

  // Get all customers by name (partial match)
  Page<CustomerResponseDTO> getCustomersByName(String name, Pageable pageable);

  // Get all customers by filters (might remove the other individual filter serach later)
  Page<CustomerResponseDTO> getCustomersByFilter(
    String name,
    Card.CardStatus cardStatus,
    Customer.KycStatus kycStatus,
    Customer.RiskProfile riskProfile,
    Pageable pageable
  );

  // Update customer profile
  CustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO updateRequest);

  // Update KYC status
  CustomerResponseDTO updateKycStatus(Long customerId, Customer.KycStatus status);

  // Update Risk Profile
  CustomerResponseDTO updateRiskProfile(Long customerId, Customer.RiskProfile profile);

  // Delete customer (this is soft delete, hard delete is prohibited)
  boolean deleteCustomer(Long id);

}
