package com.minibank.creditcard.repository;

import com.minibank.creditcard.model.AppUser;
import com.minibank.creditcard.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
