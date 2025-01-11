package dev.erikneves.desafio_bancointer.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(Lifecycle.PER_CLASS)
class CalculateUniqueDigitRequestDtoTest {

  @Test
  void itShouldReturnBigInteger() {
    // given
    var request = new CalculateUniqueDigitRequestDTO("9875", 4, Optional.empty());

    // when
    var result = request.getBigIntegerNumber();
    var integerNumber = new BigInteger("9875");

    // then
    assertEquals(result, integerNumber);
    assertEquals(request.getK(), 4);
  }

  @Test
  void itShouldReturnErrrorIfNumberIsNotParsableToBigInteger() {
    // given
    var request = new CalculateUniqueDigitRequestDTO("9875daad", 4, Optional.empty());

    // when & then
    assertThrows(IllegalArgumentException.class, () -> request.getBigIntegerNumber());
  }

  @Test
  void itShouldReturnBigIntegerRespectTheRange() {
    // given
    var bigInteger = BigInteger.TEN.pow(1000000).add(BigInteger.ONE);
    var request = new CalculateUniqueDigitRequestDTO(bigInteger.toString(), 4, Optional.empty());

    // when & then
    assertThrows(IllegalArgumentException.class, () -> {
      request.getBigIntegerNumber();
    });
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, 1000000 })
  void itShouldThrowAnErrorIfIntegerDoesntRespectTheRange(Integer i) {
    // given
    var request = new CalculateUniqueDigitRequestDTO("9875", i, Optional.empty());
    // when & then
    assertThrows(IllegalArgumentException.class, () -> request.getK());
  }
}
