package com.minibank.creditcard.model;

public class Transaction {

  // id (UUID)
  //cardId: Foreign key to Card
  //merchantName: e.g., Tokopedia, Grab
  //merchantLocation: Optional; useful for fraud rules
  //type (enum: PURCHASE, ATM_WITHDRAWAL, REFUND)
  //amount: Transaction value
  //currency: e.g., IDR, USD
  //status (enum: PENDING, SETTLED, DECLINED, REVERSED)
  //authorizedAt, settledAt: Important for billing cycle
  //referenceCode: External ref (for disputes/fraud)
  //channel: (enum: ONLINE, POS, ATM)

}
