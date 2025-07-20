package com.minibank.creditcard.dto.request;

import com.minibank.creditcard.model.Customer;

public class UpdateCustomerRequestDTO {

  private String phoneNumber;

  private String address;

  private Customer.KycStatus kycStatus;

  private Customer.RiskProfile riskProfile;

}
