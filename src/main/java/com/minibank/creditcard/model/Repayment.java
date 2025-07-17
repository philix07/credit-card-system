package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.BillingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  @OneToOne
  @JoinColumn(name = "billing_id", nullable = false)
  private BillingCycle billingCycle;

  @OneToMany(mappedBy = "repayment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RepaymentDetail> details = new ArrayList<>();

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal totalPaid;

  private LocalDateTime lastPaymentDate;

}
