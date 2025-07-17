package com.minibank.creditcard.util;

public class CardUtil {

  public static String maskCardNumber(String cardNumber) {
    if (cardNumber == null || cardNumber.length() < 10) {
      throw new IllegalArgumentException("Invalid PAN: must be at least 10 digits.");
    }

    String start = cardNumber.substring(0, 6); // First 6 digits
    String end = cardNumber.substring(cardNumber.length() - 4); // Last 4 digits
    String masked = "*".repeat(cardNumber.length() - 10); // Mask middle digits

    return start + masked + end;
  }

}
