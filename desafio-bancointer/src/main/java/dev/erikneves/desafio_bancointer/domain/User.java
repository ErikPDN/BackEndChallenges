package dev.erikneves.desafio_bancointer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "users")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
  private String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  private List<UniqueDigit> uniqueDigits = new ArrayList<>();

  public void addUniqueDigit(UniqueDigit uniqueDigit) {
    var newUniqueDigit = uniqueDigit.clone();
    newUniqueDigit.setUser(this);
    this.uniqueDigits.add(newUniqueDigit);
  }
}
