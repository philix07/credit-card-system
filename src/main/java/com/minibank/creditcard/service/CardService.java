package com.minibank.creditcard.service;

import com.minibank.creditcard.dto.response.CardResponseDTO;
import com.minibank.creditcard.model.Card;

public interface CardService {

  // 1. Get card info for a customer
  CardResponseDTO getCardByCustomerId(Long customerId);

  // 2. Block/activate/update card status
  CardResponseDTO updateCardStatus(Long customerId, Card.CardStatus newStatus);

}
