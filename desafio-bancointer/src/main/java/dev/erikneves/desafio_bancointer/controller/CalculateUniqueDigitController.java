package dev.erikneves.desafio_bancointer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erikneves.desafio_bancointer.controller.dto.CalculateUniqueDigitRequestDTO;
import dev.erikneves.desafio_bancointer.service.CalculateUniqueDigitService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/calculate-unique-digit")
public class CalculateUniqueDigitController {

  private final CalculateUniqueDigitService service;

  public CalculateUniqueDigitController(CalculateUniqueDigitService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Integer> calculateUniqueDigit(@RequestBody @Valid CalculateUniqueDigitRequestDTO request) {
    var bigInteger = request.getBigIntegerNumber();
    var k = request.getK();

    var response = this.service.calculateUniqueDigit(bigInteger, k, request.userId());
    return ResponseEntity.ok(response);
  }
}
