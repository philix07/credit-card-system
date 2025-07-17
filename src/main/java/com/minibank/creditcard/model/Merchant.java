package com.minibank.creditcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String legalName;
  private String address;

}
