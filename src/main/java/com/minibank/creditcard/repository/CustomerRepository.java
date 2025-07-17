package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
