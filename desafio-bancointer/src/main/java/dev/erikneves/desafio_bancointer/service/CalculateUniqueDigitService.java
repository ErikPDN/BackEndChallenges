package dev.erikneves.desafio_bancointer.service;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

public interface CalculateUniqueDigitService {
  int calculateUniqueDigit(BigInteger number, int k, Optional<UUID> userId);
}
