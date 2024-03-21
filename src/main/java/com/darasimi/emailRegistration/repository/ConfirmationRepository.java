package com.darasimi.emailRegistration.repository;

import com.darasimi.emailRegistration.entity.Confirmation;
import com.darasimi.emailRegistration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
