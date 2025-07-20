package com.minibank.creditcard.dto.response;

import com.minibank.creditcard.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardResponseDTO {

  private String id;
  private String cardNumber;
  private BigDecimal cardLimit;
  private BigDecimal availableLimit;
  private Card.CardStatus cardStatus;
  private LocalDate issuedDate;
  private LocalDate expiryDate;

}
