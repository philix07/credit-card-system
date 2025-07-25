package com.minibank.creditcard.exception.handler;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

  private final int status;
  private final LocalDateTime timestamp;
  private final String error;
  private final String message;
  private final String path;

}
