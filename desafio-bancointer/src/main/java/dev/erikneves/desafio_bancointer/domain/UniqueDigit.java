package dev.erikneves.desafio_bancointer.domain;

import java.math.BigInteger;

public class UniqueDigit {
  private int result;

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
