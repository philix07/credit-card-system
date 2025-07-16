package com.minibank.creditcard.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class AppUser {

  // id (UUID)
  // username: Unique login name
  // email
  // password
  // role (enum: ADMIN, FRAUD_ANALYST, AUDITOR)
  // permissions: JSON or EnumSet
  // createdAt, lastLoginAt

}


