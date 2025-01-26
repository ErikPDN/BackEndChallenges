package dev.erik.emailms.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.erik.emailms.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
