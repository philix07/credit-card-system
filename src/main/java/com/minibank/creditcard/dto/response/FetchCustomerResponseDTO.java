package com.minibank.creditcard.dto.response;

import com.minibank.creditcard.model.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FetchCustomerResponseDTO {

  private String id;
  private String fullName;
  private String phoneNumber;
  private String nik;
  private String address;
  private LocalDate dateOfBirth;
  private Customer.KycStatus kycStatus;
  private Customer.RiskProfile riskProfile;

}
