package com.minibank.creditcard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CustomerRegistrationRequest {

  private CreateCustomerDTO createCustomerDTO;
  private CreateCardDTO createCardDTO;

  @Getter
  @NoArgsConstructor
  public static class CreateCustomerDTO {
    private String nik;
    private String fullName;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
  }

  @Getter
  @NoArgsConstructor
  public static class CreateCardDTO {
    private String pin;
    private BigDecimal monthlyIncome;
    private BigDecimal requestedLimit;
  }

}
