package com.minibank.creditcard.controller;

import com.minibank.creditcard.dto.request.customer.CreateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.customer.UpdateCustomerRequestDTO;
import com.minibank.creditcard.dto.request.customer.UpdateKycStatusRequestDTO;
import com.minibank.creditcard.dto.request.customer.UpdateRiskProfileRequestDTO;
import com.minibank.creditcard.dto.response.CustomerResponseDTO;
import com.minibank.creditcard.model.Customer;
import com.minibank.creditcard.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

  private final CustomerService customerService;

  // ----------------------------------------------------------------------------------------------
  // -------------- -> Registration Endpoints <- --------------------------------------------------
  // ----------------------------------------------------------------------------------------------

  @PostMapping(value = "customers/register", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> saveCustomer(@Valid @RequestBody CreateCustomerRequestDTO request) {
    CustomerResponseDTO createdCustomer = customerService.registerCustomer(request);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(createdCustomer.getId())
      .toUri();

    return ResponseEntity.created(location).body(createdCustomer);
  }

  // ----------------------------------------------------------------------------------------------
  // -------------- -> Basic Retrieve Endpoints <- ------------------------------------------------
  // ----------------------------------------------------------------------------------------------

  @GetMapping(value = "customers/{id}", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {

  }

  @GetMapping(value = "customers/{phoneNumber}", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {

  }

  @GetMapping(value = "customers/{nik}", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> getCustomerByNIK(@PathVariable String nik) {

  }

  // ----------------------------------------------------------------------------------------------
  // -------------- -> Filtering / Searching Endpoints (Paged) <- ------------------------------------------------------
  // ----------------------------------------------------------------------------------------------

  @GetMapping(value = "customers/search", consumes = "application/json")
  public ResponseEntity<Page<CustomerResponseDTO>> getCustomerByName(@PathVariable String name) {

  }

  // ----------------------------------------------------------------------------------------------
  // -------------- -> Update Endpoints <- --------------------------------------------------------
  // ----------------------------------------------------------------------------------------------

  @PatchMapping(value = "customers/{id}", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> updateCustomersDetail(
    @PathVariable Long id,
    @Valid @RequestBody UpdateCustomerRequestDTO updateRequest
  ) {
    return ResponseEntity.ok(customerService.updateCustomer(id, updateRequest));
  }

  @PatchMapping(value = "customers/{id}/kyc-status", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> updateCustomersKycStatus(
    @PathVariable Long id,
    @Valid @RequestBody UpdateKycStatusRequestDTO updateKycStatusRequest
  ) {
    return ResponseEntity.ok(customerService.updateKycStatus(id, updateKycStatusRequest.getKycStatus()));
  }

  @PatchMapping(value = "customers/{id}/risk-profile", consumes = "application/json")
  public ResponseEntity<CustomerResponseDTO> updateCustomersRiskProfile(
    @PathVariable Long id,
    @Valid @RequestBody UpdateRiskProfileRequestDTO updateRiskProfileRequest
  ) {
    return ResponseEntity.ok(customerService.updateRiskProfile(id, updateRiskProfileRequest.getRiskProfile()));
  }

  // ----------------------------------------------------------------------------------------------
  // -------------- -> Delete Endpoints <- --------------------------------------------------------
  // ----------------------------------------------------------------------------------------------

  @DeleteMapping(value = "customer/{id}", consumes = "application/json")
  public boolean deleteCustomerById(@PathVariable Long id) {
    return customerService.deleteCustomer(id);
  }

}
