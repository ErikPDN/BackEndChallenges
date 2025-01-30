package dev.erik.twitter_simplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.erik.twitter_simplificado.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
