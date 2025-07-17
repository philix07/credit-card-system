package com.minibank.creditcard.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "roles")
public class Role extends BaseEntity{

  @Column(nullable = false, unique = true)
  private String name; // e.g. "ROLE_USER", "ROLE_ADMIN"

}
