package dev.erik.userms.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.erik.userms.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
  Optional<UserModel> findByEmail(String email);
}
