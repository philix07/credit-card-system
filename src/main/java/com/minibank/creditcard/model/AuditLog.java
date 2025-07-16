package com.minibank.creditcard.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "audit_log")
public class AuditLog {

  // id (UUID)
  // entityType: e.g., CARD, CUSTOMER, ADMIN
  // entityId: UUID of affected entity
  // changeType (enum: CREATE, UPDATE, DELETE)
  // oldValue: JSON snapshot
  // newValue: JSON snapshot
  // changedBy: Admin or service
  // timestamp

}
