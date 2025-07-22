package com.minibank.creditcard.dto.request.customer;

import com.minibank.creditcard.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCustomerRequestDTO {

  private String phoneNumber;

  private String address;

  private Customer.KycStatus kycStatus;

  private Customer.RiskProfile riskProfile;

}
