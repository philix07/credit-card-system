package com.minibank.creditcard.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
public class Customer extends BaseEntity {

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String phoneNumber;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private KycStatus kycStatus;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RiskProfile riskProfile;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime updatedAt;

  // Enums for RiskProfile statuses
  public enum KycStatus {
    PENDING, APPROVED, REJECTED
  }

  // Enums for RiskProfile statuses
  public enum RiskProfile {
    LOW, MEDIUM, HIGH
  }
}


