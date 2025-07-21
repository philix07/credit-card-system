package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  // Check duplicate phoneNumber
  boolean existsByPhoneNumber(String phoneNumber);

  // Check duplicate NIK
  boolean existsByNik(String nik);

  // Search by phoneNumber
  Optional<Customer> findByPhoneNumber(String phoneNumber);

  // Search by NIK
  Optional<Customer> findByNik(String nik);

  // Search by name (partial match)
  List<Customer> findByFullNameContainingIgnoreCase(String name);

  // Filtering by KycStatus
  List<Customer> findByKycStatus(Customer.KycStatus status);

  // Filtering by RiskProfile
  List<Customer> findByRiskProfile(Customer.RiskProfile profile);

  // Fetch only active customers with pagination
  Page<Customer> findByStatus(Card.CardStatus status, Pageable pageable);

  @Override
  default void delete(Customer entity) {
    throw new UnsupportedOperationException("Hard delete is not allowed");
  }
}
