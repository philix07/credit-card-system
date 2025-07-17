package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.BillingCycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingCycleRepository extends JpaRepository<BillingCycle, Long> {
}
