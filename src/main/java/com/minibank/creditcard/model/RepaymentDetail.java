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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "repayment_id", nullable = false)
  private Repayment repayment;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal paidAmount;

  @Enumerated(EnumType.STRING)
  private RepaymentType paymentStatus;

  public enum RepaymentType {
    FULL, MINIMUM, PARTIAL
  }

}
