package com.minibank.creditcard.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * We are keeping this class simple because we just want to track,
 * which Merchant did the transactions
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "merchants")
public class Merchant extends BaseEntity{

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String legalName;

  @Column(nullable = false)
  private String address;

}
