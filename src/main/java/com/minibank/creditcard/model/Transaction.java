package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.TransactionStatus;
import com.minibank.creditcard.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "transactions")
public class Transaction extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "billing_id")
  private BillingCycle billingCycle;

  // I think this is supposed to be ManyToMany
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id")
  private Merchant merchant;

  @CreatedDate
  private Instant transactionDate;

  @Column(nullable = false)
  private String itemName;

  @Column(nullable = false)
  private int itemQty;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal unitPrice;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal paidAmount = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType; // PURCHASE, ATM_WITHDRAWAL, REFUND

  @Enumerated(EnumType.STRING)
  private TransactionStatus transactionStatus; // PENDING, SETTLED, DECLINED

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RepaymentStatus repaymentStatus;

  public enum RepaymentStatus {
    FULL, PARTIAL, UNPAID
  }
}
