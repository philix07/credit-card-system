package com.minibank.creditcard.model;

public class Card {

  //id (UUID)
  //customerId: Foreign key to Customer
  //cardNumber: 16-digit PAN (masked in API)
  //cvv: Encrypted string (never stored raw)
  //expiryDate: Format MM/YY
  //pinHash: Securely stored hash for card PIN
  //creditLimit: Total assigned limit
  //availableLimit: Remaining usable limit
  //status (enum: INACTIVE, ACTIVE, BLOCKED, EXPIRED)
  //issuedDate, activatedDate

}
