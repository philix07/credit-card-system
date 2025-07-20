package com.minibank.creditcard.service;

import com.minibank.creditcard.dto.request.CreateCustomerRequest;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;

public interface CustomerService {

  public CustomerResponseDTO registerCustomer(CreateCustomerRequest registrationRequest);

}
