package com.minibank.creditcard.model;

import com.minibank.creditcard.model.enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cards")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  // Owning side of the relationship. You could use customerId directly, but using a full entity allows richer joins and object navigation.
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(unique = true, nullable = false, length = 16)
  private String cardNumber; // Primary Account Number (masked in API layer)

  @Column(nullable = false)
  private String cvv; // Encrypted CVV (do NOT store raw CVV)

  @Column(nullable = false)
  private String pin; // Hashed PIN (bcrypt)

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal cardLimit;

  @Column(nullable = false, precision = 18, scale = 0)
  private BigDecimal availableLimit;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CardStatus status; // INACTIVE, ACTIVE, BLOCKED, EXPIRED

  private LocalDate issuedDate;

  @Column(nullable = false, length = 5)
  private String expiryDate; // Format: MM/YY

  @PrePersist
  protected void onCreate() {
    this.issuedDate = LocalDate.now();
  }

}
