package com.minibank.creditcard.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String phoneNumber;

  @Column(length = 512)
  private String address;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private KycStatus kycStatus;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RiskProfile riskProfile;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
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


