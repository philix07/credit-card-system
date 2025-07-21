package com.minibank.creditcard.controller;

import com.minibank.creditcard.dto.request.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("customers")
  public ResponseEntity<CustomerResponseDTO> saveCustomer(@Valid @RequestBody CreateCustomerRequestDTO request) {
    CustomerResponseDTO createdCustomer = customerService.registerCustomer(request);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(createdCustomer.getId())
      .toUri();

    return ResponseEntity.created(location).body(createdCustomer);
  }

}
