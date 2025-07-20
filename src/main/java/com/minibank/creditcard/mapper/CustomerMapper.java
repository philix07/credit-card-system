package com.minibank.creditcard.mapper;

import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.model.Customer;

public class CustomerMapper {

  public static CustomerResponseDTO mapCustomerToDTO(Customer customer) {
    return CustomerResponseDTO.builder()
      .id(customer.getId())
      .card_id(customer.getCard().getId())
      .nik(customer.getNik())
      .fullName(customer.getFullName())
      .phoneNumber(customer.getPhoneNumber())
      .address(customer.getAddress())
      .dateOfBirth(customer.getDateOfBirth())
      .kycStatus(customer.getKycStatus())
      .riskProfile(customer.getRiskProfile())
      .build();
  }
}
