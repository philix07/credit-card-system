package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an individual repayment transaction made by a customer.
 * RepaymentDetails are tied to a single Repayment and can optionally apply to a specific past cycle.
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "repayment_details")
public class RepaymentDetail extends BaseEntity{

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "repayment_id", nullable = false)
  private Repayment repayment;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal paidAmount;

  @CreatedDate
  private Instant paidOn;

}
