package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Entity(name = "repayment_details")
public class RepaymentDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "billing_cycle_id")
  private BillingCycle billingCycle;

  @OneToMany(mappedBy = "repaymentSummary", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Repayment> repayments = new ArrayList<>();

  private BigDecimal totalPaid;
  private LocalDate lastPaymentDate;

  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus; // FULL_PAID, MINIMUM_PAID, etc.


}
