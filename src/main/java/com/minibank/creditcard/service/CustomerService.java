package com.minibank.creditcard.service;

import com.minibank.creditcard.dto.request.CustomerRegistrationRequest;

public interface CustomerService {

  public void registerCustomer(CustomerRegistrationRequest registrationRequest);

}
