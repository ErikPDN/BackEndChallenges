package dev.erikneves.desafio_bancointer.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.Optional;

import dev.erikneves.desafio_bancointer.service.CalculateUniqueDigitService;
import dev.erikneves.desafio_bancointer.controller.dto.CalculateUniqueDigitRequestDTO;

@TestInstance(Lifecycle.PER_CLASS)
public class CalculateUniqueDigitControllerTest {
  private CalculateUniqueDigitController controller;

  @Mock
  private CalculateUniqueDigitService service;

  @BeforeAll
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.controller = new CalculateUniqueDigitController(this.service);
  }

  @BeforeEach
  void resetMocks() {
    reset(this.service);
  }

  @Test
  void itShouldReturnUniqueDigit() {
    // given
    var request = new CalculateUniqueDigitRequestDTO("9875", 4, Optional.empty());
    when(this.service.calculateUniqueDigit(any(BigInteger.class), anyInt(), any()))
        .thenReturn(8);

    // when
    var response = this.controller.calculateUniqueDigit(request);
    var body = response.getBody();

    // then
    assertEquals(8, body);
    verify(this.service, times(1))
        .calculateUniqueDigit(any(BigInteger.class), anyInt(), any());
  }
}
