package com.minibank.creditcard.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String parameter, Object value) {
    super("Customer with " + parameter + ": " + value + " not found");
  }
}
