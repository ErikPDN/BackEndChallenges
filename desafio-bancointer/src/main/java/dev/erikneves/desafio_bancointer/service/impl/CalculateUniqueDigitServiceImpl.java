package dev.erikneves.desafio_bancointer.service.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.erikneves.desafio_bancointer.domain.UniqueDigit;
import dev.erikneves.desafio_bancointer.repository.UserRepository;
import dev.erikneves.desafio_bancointer.service.CacheService;
import dev.erikneves.desafio_bancointer.service.CalculateUniqueDigitService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateUniqueDigitServiceImpl implements CalculateUniqueDigitService {

  private final UserRepository userRepository;
  private final CacheService<UniqueDigit> cacheService;

  @Override
  public int calculateUniqueDigit(BigInteger number, int k, Optional<UUID> userId) {
    var cacheKey = this.cacheKey(number, k);
    var uniqueDigit = this.cacheService.get(cacheKey)
        .orElseGet(() -> this.createNewUniqueDigit(number, k));

    if (userId.isPresent()) {
      this.userRepository.findById(userId.get())
          .ifPresent(user -> {
            user.addUniqueDigit(uniqueDigit);
            this.userRepository.save(user);
          });
    }

    return uniqueDigit.getResult();
  }

  private String cacheKey(BigInteger number, int k) {

    return new StringBuilder()
        .append("uniquedigit::")
        .append("number::")
        .append(String.valueOf(number))
        .append("::k::")
        .append(String.valueOf(k))
        .toString();
  }

  private UniqueDigit createNewUniqueDigit(BigInteger number, int k) {
    var uniqueDigit = new UniqueDigit(number, k);
    var cacheKey = this.cacheKey(number, k);
    this.cacheService.put(cacheKey, uniqueDigit);
    return uniqueDigit;
  }
}
