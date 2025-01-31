package dev.erik.twitter_simplificado.services.impl;

import java.time.Instant;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import dev.erik.twitter_simplificado.controllers.dtos.LoginResponseDTO;
import dev.erik.twitter_simplificado.repositories.UserRepository;
import dev.erik.twitter_simplificado.services.TokenService;
import dev.erik.twitter_simplificado.services.exceptions.PasswordNotMatchException;
import dev.erik.twitter_simplificado.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

  private final JwtEncoder jwtEncoder;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Value("${jwt.expiration.time}")
  private Long jwtExpirationTime;

  @Override
  public LoginResponseDTO login(String username, String password) {
    log.info("Login attempt for user: {}", username);
    var user = this.userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new PasswordNotMatchException("Incorrect password");
    }

    log.info("User authenticated: {}", username);

    var expiresIn = jwtExpirationTime;
    var now = Instant.now();

    var claims = JwtClaimsSet.builder()
        .issuer("twitter-simplificado-api")
        .subject(user.getUserId().toString())
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiresIn))
        .build();

    var jwtValue = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    return new LoginResponseDTO(jwtValue, expiresIn);
  }
}
