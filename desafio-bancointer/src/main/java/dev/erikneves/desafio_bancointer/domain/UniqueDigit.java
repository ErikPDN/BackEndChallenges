package dev.erikneves.desafio_bancointer.domain;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unique_digits")
@Data
@NoArgsConstructor
public class UniqueDigit implements Cloneable {
  @Setter
  @Getter
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Getter
  @Column(name = "result", nullable = false, columnDefinition = "int")
  private int result;

  @Setter
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = true)
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
