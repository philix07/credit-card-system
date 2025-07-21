package com.minibank.creditcard.exception.handler;

import com.minibank.creditcard.exception.DuplicateNIKException;
import com.minibank.creditcard.exception.DuplicatePhoneNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // -------------------------------------------------------------
  // ------ -> General Exception Handler <- ----------------------
  // -------------------------------------------------------------

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.BAD_REQUEST.value(),
      LocalDateTime.now(),
      ex.getClass().getSimpleName(),
      ex.getMessage(),
      request.getDescription(false).replace("uri=", "")
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
    // Getting all validation error messages
    List<String> errorMessages = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> error.getField() + ": " + error.getDefaultMessage())
      .toList();

    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.BAD_REQUEST.value(),
      LocalDateTime.now(),
      "Validation Exception",
      String.join(", ", errorMessages),
      request.getDescription(false).replace("uri=", "")
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  // -------------------------------------------------------------
  // ------ -> Customer Exception Handler <- ---------------------
  // -------------------------------------------------------------

  @ExceptionHandler(DuplicatePhoneNumberException.class)
  public ResponseEntity<ErrorResponse> handleDuplicatePhoneNumber(DuplicatePhoneNumberException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.CONFLICT.value(),
      LocalDateTime.now(),
      ex.getClass().getSimpleName(),
      ex.getMessage(),
      request.getDescription(false).replace("uri=", "")
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

  @ExceptionHandler(DuplicateNIKException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateNIK(DuplicateNIKException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.CONFLICT.value(),
      LocalDateTime.now(),
      ex.getClass().getSimpleName(),
      ex.getMessage(),
      request.getDescription(false).replace("uri=", "")
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

}
