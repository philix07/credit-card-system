package com.minibank.creditcard.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class AppUser {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

//  @ElementCollection(fetch = FetchType.EAGER)
//  @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
//  @Column(name = "permission")
//  @Enumerated(EnumType.STRING)
//  private Set<Permission> permissions;
//
//  @CreationTimestamp
//  @Column(nullable = false, updatable = false)
//  private Instant createdAt;
//
//  private Instant lastLoginAt;

  public enum Role {
    ADMIN,
    FRAUD_ANALYST,
    AUDITOR
  }

  public enum Permission {
    VIEW_TRANSACTIONS,
    FLAG_SUSPICIOUS,
    GENERATE_REPORTS,
    VIEW_AUDIT_LOGS,
    MANAGE_USERS
  }

}


