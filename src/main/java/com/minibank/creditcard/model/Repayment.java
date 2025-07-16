package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.BillingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents an individual repayment transaction made by a customer.
 * Payments are tied to a summary and can optionally apply to a specific past cycle.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "repayments")
public class Repayment {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;

  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate dueDate;

  private BigDecimal totalDue;
  private BigDecimal unpaidFromLastCycle;
  private BigDecimal interestApplied;
  private BigDecimal minimumPayment;
  private BigDecimal interestRate;

  @Enumerated(EnumType.STRING)
  private BillingStatus status; // OPEN, GENERATED, PAID, OVERDUE

  private LocalDate generatedAt;

  @OneToOne(mappedBy = "billingCycle", cascade = CascadeType.ALL, orphanRemoval = true)
  private RepaymentDetail repaymentDetail;

}
