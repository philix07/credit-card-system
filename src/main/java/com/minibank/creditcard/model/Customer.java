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

  @OneToOne(
    mappedBy = "customer",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    optional = false
  )
  private Card card;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String phoneNumber;

  @Column(nullable = false, unique = true)
  private String nik;

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

  // Enums for RiskProfile statuses
  public enum KycStatus {
    PENDING, APPROVED, REJECTED
  }

  // Enums for RiskProfile statuses
  public enum RiskProfile {
    NOT_SET, LOW, MEDIUM, HIGH
  }

}


