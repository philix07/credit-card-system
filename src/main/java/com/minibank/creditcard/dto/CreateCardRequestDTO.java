package com.minibank.creditcard.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class CreateCardRequestDTO {
  private CreateCustomerDTO createCustomerDTO;
  private CreateCardDTO createCardDTO;
}

@Getter
class CreateCustomerDTO {

  private String nik;
  private String fullName;
  private String phoneNumber;
  private String address;
  private LocalDate dateOfBirth;

}

@Getter
class CreateCardDTO {

  private String pin;
  private BigDecimal cardLimit;

}
