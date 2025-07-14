package com.minibank.creditcard.model;

public class BillingCycle {

  // id (UUID)
  // cardId: Foreign key to Card
  // startDate, endDate: Defines the billing window
  // dueDate: Payment deadline (usually 10 days after end)
  // totalSpending: Total spending amount in this cycle
  // totalDue: Total unpaid amount in this cycle
  // minPayment: Usually 10% of totalDue
  // status (enum: OPEN, GENERATED, PAID, OVERDUE)

}
