package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
