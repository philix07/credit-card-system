package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.RepaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepaymentDetailRepository extends JpaRepository<RepaymentDetail, Long> {
}
