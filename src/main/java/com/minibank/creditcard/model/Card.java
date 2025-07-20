package com.minibank.creditcard.model;

import com.minibank.creditcard.util.CardUtil;
import com.minibank.creditcard.util.DateUtil;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cards")
public class Card extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(unique = true, nullable = false, length = 16)
  private String cardNumber; // Primary Account Number (masked in API layer)

  @Column(nullable = false)
  private String cvv; // Never store CVV (do NOT store CVV, this is for testing purpose)

  @Column(nullable = false)
  private String pin; // Hashed PIN (bcrypt)

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal cardLimit;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal availableLimit;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CardStatus status;

  @Column(nullable = false)
  private LocalDate issuedDate;

  @Column(nullable = false, length = 5)
  private String expiryDate; // Format: MM/YY

  public enum CardStatus {
    PENDING_APPROVAL, REJECTED, INACTIVE, ACTIVE, BLOCKED, EXPIRED
  }

}
