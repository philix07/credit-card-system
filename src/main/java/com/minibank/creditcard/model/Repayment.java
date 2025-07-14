package com.minibank.creditcard.model;

public class Repayment {

  // id (UUID)
  // cardId: Foreign key to Card
  // billingCycleId: Foreign key to BillingCycle
  // amount: Paid by customer
  // paidOn: Timestamp of payment
  // method (enum: BANK_TRANSFER, AUTO_DEBIT, E-WALLET)

}
