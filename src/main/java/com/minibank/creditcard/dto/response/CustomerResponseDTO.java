package com.minibank.creditcard.dto.response;

import com.minibank.creditcard.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerResponseDTO {

  private UUID id;
  private UUID card_id;
  private String nik;
  private String fullName;
  private String phoneNumber;
  private String address;
  private LocalDate dateOfBirth;
  private Customer.KycStatus kycStatus;
  private Customer.RiskProfile riskProfile;

}
