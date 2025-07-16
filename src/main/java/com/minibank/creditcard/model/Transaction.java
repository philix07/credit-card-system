package com.minibank.creditcard.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "transactions")
public class Transaction {

  // id (UUID)
  //cardId: Foreign key to Card
  //merchantName: e.g., Tokopedia, Grab
  //merchantLocation: Optional; useful for fraud rules
  //type (enum: PURCHASE, ATM_WITHDRAWAL, REFUND)

  //status (enum: PENDING, SETTLED, DECLINED, REVERSED)
  //authorizedAt, settledAt: Important for billing cycle
  //referenceCode: External ref (for disputes/fraud)
  //channel: (enum: ONLINE, POS, ATM)
  
  private LocalDate transactionDate;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
