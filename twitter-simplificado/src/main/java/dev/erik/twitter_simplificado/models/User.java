package dev.erik.twitter_simplificado.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import dev.erik.twitter_simplificado.models.Role;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userId;

  private String username;

  private String password;

  private Set<Role> roles;
}
