package com.minibank.creditcard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CardUtil {

  public static String generateExpiryDate(LocalDate issuedDate) {
    DateTimeFormatter MM_YY_FORMATTER = DateTimeFormatter.ofPattern("MM/yy");
    LocalDate expiryDate = issuedDate.plusYears(5);
    return expiryDate.format(MM_YY_FORMATTER);
  }

  /**
   * Generates a 16-digit card number where last 4 digits are expiry date in MMyy format.
   * The first 12 digits are random numbers.
   *
   * @return String 16-digit card number
   */
  public static String generateCardNumber() {
    DateTimeFormatter EXPIRY_FORMATTER = DateTimeFormatter.ofPattern("MMyy");
    Random random = new Random();

    LocalDate expiryDate = LocalDate.now().plusYears(5);
    String expiryDigit = expiryDate.format(EXPIRY_FORMATTER); // e.g. "1025" equals October 2025

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 12; i++) {
      sb.append(random.nextInt(10)); // random digit from 0-9
    }
    sb.append(expiryDigit);
    return sb.toString();
  }

  public static String maskCardNumber(String cardNumber) {
    if (cardNumber == null || cardNumber.length() < 10) {
      throw new IllegalArgumentException("Invalid PAN: must be at least 10 digits.");
    }

    String start = cardNumber.substring(0, 6); // First 6 digits
    String end = cardNumber.substring(cardNumber.length() - 4); // Last 4 digits
    String masked = "*".repeat(cardNumber.length() - 10); // Mask middle digits

    return start + masked + end;
  }

  /**
   * Generate and formats the number as a 3-digit string with leading zeroes if needed.
   * For example:
   * 7 becomes "007"
   * 42 becomes "042"
   * 999 stays "999"
   */
  public static String generateCVV() {
    return String.format("%03d", new Random().nextInt(1000));
  }

  /**
   * Calculates the approved credit card limit based on the user's monthly income and requested limit.
   *
   * @param monthlyIncome  the user's declared monthly income (must be greater than 0)
   * @param requestedLimit the limit the user wants to request (can be null or greater than 0)
   * @return the approved card limit, capped at 3x the monthly income if needed
   * @throws IllegalArgumentException if monthly income is null or less than or equal to zero
   */
  public static BigDecimal calculateCardLimit(BigDecimal monthlyIncome, BigDecimal requestedLimit) {
    // Check that monthly income is not null and greater than 0
    if (monthlyIncome == null || monthlyIncome.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Monthly income must be greater than 0.");
    }

    // Calculate the maximum allowed card limit based on income * 3
    BigDecimal maxAllowedLimit = monthlyIncome.multiply(BigDecimal.valueOf(3));

    // If the user has requested a specific limit and it is positive (greater than 0)
    if (requestedLimit != null && requestedLimit.compareTo(BigDecimal.ZERO) > 0) {
      // If requested limit is less than or equal to the allowed max, approve it
      if (requestedLimit.compareTo(maxAllowedLimit) <= 0) {
        return requestedLimit.setScale(0, RoundingMode.HALF_UP);
      } else {
        // Otherwise, cap the approved limit to the maximum allowed
        return maxAllowedLimit.setScale(0, RoundingMode.HALF_UP);
      }
    }

    // If no valid requested limit is provided, return the maximum allowed limit
    return maxAllowedLimit.setScale(0, RoundingMode.HALF_UP);
  }
}
