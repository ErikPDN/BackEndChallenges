package dev.erikneves.desafio_bancointer.domain;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "unique_digits")
public class UniqueDigit {
  @Id
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "varchar(36)")
  private UUID id;

  @Column(name = "result", nullable = false, columnDefinition = "int")
  private int result;

  @ManyToOne
  @JoinColumn(name = "user_id", columnDefinition = "varchar(36)")
  private User user;

  public UniqueDigit(BigInteger number, Integer k) {
    this.result = this.calculateNewUniqueDigit(number, k);
  }

  private int calculateNewUniqueDigit(BigInteger number, int k) {
    // Repetir o número k vezes e calcular a soma inicial
    int uniqueDigit = sumOfDigits(String.valueOf(number).repeat(k));

    // Continuar somando os dígitos enquanto o número for maior que 9
    while (uniqueDigit > 9) {
      uniqueDigit = sumOfDigits(String.valueOf(uniqueDigit));
    }

    return uniqueDigit;
  }

  private int sumOfDigits(String numberString) {
    return numberString.chars()
        .map(Character::getNumericValue)
        .sum();
  }

  public int getResult() {
    return this.result;
  }
}
