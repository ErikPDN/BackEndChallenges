package dev.erikneves.desafio_bancointer.domain;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unique_digits")
@Data
@NoArgsConstructor
public class UniqueDigit {
  @Id
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "varchar(36)")
  private UUID id = UUID.randomUUID();

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Column(name = "result", nullable = false, columnDefinition = "int")
  private int result;

  @ManyToOne
  @JoinColumn(name = "user_id", columnDefinition = "varchar(36)", nullable = true)
  private User user;

  @Column(name = "number", nullable = false, columnDefinition = "TEXT")
  private String number;

  @Column(name = "k", nullable = false, columnDefinition = "int")
  private int k;

  public UniqueDigit(BigInteger number, Integer k) {
    this.number = String.valueOf(number);
    this.k = k;
    this.result = this.calculateNewUniqueDigit(number, k);
  }

  public int getResult() {
    return this.result;
  }

  public void setUser(User user) {
    this.user = user;
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

  @Override
  public UniqueDigit clone() {
    var newUniqueDigit = new UniqueDigit();
    newUniqueDigit.setResult(this.result);
    newUniqueDigit.setUser(this.user);
    newUniqueDigit.setNumber(this.number);
    newUniqueDigit.setK(this.k);
    return newUniqueDigit;
  }
}
