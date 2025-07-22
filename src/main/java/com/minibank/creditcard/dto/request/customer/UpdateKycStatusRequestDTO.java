package com.minibank.creditcard.dto.request.customer;

import com.minibank.creditcard.model.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateKycStatusRequestDTO {

  @NotNull(message = "KycStatus must not be null")
  private Customer.KycStatus kycStatus;

}
