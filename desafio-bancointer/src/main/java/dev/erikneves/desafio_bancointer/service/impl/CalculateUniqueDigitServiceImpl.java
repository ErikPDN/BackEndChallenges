package dev.erikneves.desafio_bancointer.service.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.erikneves.desafio_bancointer.domain.UniqueDigit;
import dev.erikneves.desafio_bancointer.service.CalculateUniqueDigitService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateUniqueDigitServiceImpl implements CalculateUniqueDigitService {

  @Override
  public int calculateUniqueDigit(BigInteger number, int k, Optional<UUID> userId) {
    var uniqueDigit = new UniqueDigit(number, k);

    return uniqueDigit.getResult();
  }

}
