package dev.erikneves.desafio_bancointer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.erikneves.desafio_bancointer.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
