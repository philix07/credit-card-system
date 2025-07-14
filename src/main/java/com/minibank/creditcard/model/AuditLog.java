package com.minibank.creditcard.model;

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
