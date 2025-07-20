package com.minibank.creditcard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtil {

  public static String hashElement(String rawElement) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(rawElement);
  }

  public static boolean matchedHash(String rawElement, String hashValue) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(rawElement, hashValue);
  }

}
