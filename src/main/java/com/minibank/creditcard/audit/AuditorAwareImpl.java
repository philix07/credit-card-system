package com.minibank.creditcard.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    // When we have implemented spring security, we will change it into this
    // Optional.of(SecurityContextHolder.getContext().getAuthentication().getName())
    return Optional.of("SYSTEM");
  }
}
