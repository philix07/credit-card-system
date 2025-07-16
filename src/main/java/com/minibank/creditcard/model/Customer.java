package com.minibank.creditcard.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
public class Customer {

  // id (UUID): Unique identifier
  // fullName: Full legal name
  // email: Contact email (unique)
  // phoneNumber: Mobile number (for OTP/alerts)
  // address: Residential address
  // dateOfBirth: For age validation & credit profiling
  // kycStatus (enum: PENDING, APPROVED, REJECTED)
  // riskProfile (enum: LOW, MEDIUM, HIGH): Affects credit limit
  // createdAt, updatedAt

}
