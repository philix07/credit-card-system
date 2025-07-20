package com.minibank.creditcard.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateCustomerRequest {

  @Valid
  @NotNull
  private CreateCustomerDTO createCustomerDTO;

  @Valid
  @NotNull
  private CreateCardDTO createCardDTO;

  @Getter
  @NoArgsConstructor
  public static class CreateCustomerDTO {

    @NotBlank(message = "NIK is required")
    @Pattern(regexp = "\\d{16}", message = "NIK must be exactly 16 digits")
    private String nik;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "0\\d{9,14}", message = "Phone number must start with 0 and be 10 to 15 digits long")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

  }

  @Getter
  @NoArgsConstructor
  public static class CreateCardDTO {

    @NotBlank(message = "PIN is required")
    @Pattern(regexp = "\\d{6}", message = "PIN must be exactly 6 digits")
    private String pin;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly income must be greater than 0")
    private BigDecimal monthlyIncome;

    @NotNull(message = "Requested limit is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Requested limit must be greater than 0")
    private BigDecimal requestedLimit;

  }

}
