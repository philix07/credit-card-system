package com.minibank.creditcard.exception;

public class DuplicateNIKException extends RuntimeException {
  public DuplicateNIKException(String nik) {
    super("NIK already exists: " + nik);
  }
}
