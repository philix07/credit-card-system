package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.BillingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a specific billing cycle for a credit card.
 * Contains system-generated financial summary of a period (e.g., monthly statement).
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "billing_cycles")
public class BillingCycle extends BaseEntity{

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;

  private LocalDate startDate;

  private LocalDate endDate;

  private LocalDate dueDate;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal totalDue;

  private LocalDate generatedAt;

  @Enumerated(EnumType.STRING)
  private BillingStatus status; // OPEN, GENERATED, PAID, OVERDUE

  @OneToOne(mappedBy = "billingCycle", cascade = CascadeType.ALL, orphanRemoval = true)
  private Repayment repayment;

}
