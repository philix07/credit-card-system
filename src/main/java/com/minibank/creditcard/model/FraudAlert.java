package com.minibank.creditcard.model;

import jakarta.persistence.Entity;
import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity(name = "fraud_alerts")
public class FraudAlert {

  // id (UUID)
  // transactionId: Linked to Transaction
  // reason: Description of the rule triggered
  // severity: (LOW, MEDIUM, HIGH)
  // createdAt: Timestamp
  // resolved: Boolean
  // resolvedBy, resolvedAt: Admin tracking

}
