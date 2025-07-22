package com.minibank.creditcard.dto.request.customer;

import com.minibank.creditcard.model.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRiskProfileRequestDTO {

  @NotNull(message = "RiskProfile must not be null")
  private Customer.RiskProfile riskProfile;

}
