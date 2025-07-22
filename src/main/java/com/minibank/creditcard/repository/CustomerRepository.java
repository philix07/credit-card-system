package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.Card;
import com.minibank.creditcard.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

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
  Page<Customer> findByFullNameContainingIgnoreCase(String name, Pageable pageable);

  // Filtering by KycStatus
  Page<Customer> findByKycStatus(Customer.KycStatus status, Pageable pageable);

  // Filtering by RiskProfile
  Page<Customer> findByRiskProfile(Customer.RiskProfile profile, Pageable pageable);

  @Query("SELECT c FROM Customer c JOIN c.card card WHERE card.status = :status")
  Page<Customer> findByCardStatus(@Param("status") Card.CardStatus status, Pageable pageable);

  @Override
  default void delete(Customer entity) {
    throw new UnsupportedOperationException("Hard delete is not allowed");
  }
}
