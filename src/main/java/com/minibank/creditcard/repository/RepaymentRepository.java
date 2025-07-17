package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
}
