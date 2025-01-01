package dev.erikneves.desafio_bancointer.domain;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class User {
  private UUID id;
  private String name;
  private String email;
  private List<UniqueDigit> uniqueDigits;
}
