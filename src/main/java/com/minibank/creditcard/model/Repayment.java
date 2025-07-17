package com.minibank.creditcard.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Represents a summary of how a customer repaid a specific billing cycle.
 * Tracks total paid, payment status, and links to detailed repayments.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "repayments")
public class Repayment extends BaseEntity{

  @OneToOne
  @JoinColumn(name = "billing_id", nullable = false)
  private BillingCycle billingCycle;

  @OneToMany(mappedBy = "repayment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RepaymentDetail> details = new ArrayList<>();

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal totalPaid;

  private LocalDateTime lastPaymentDate;

}
