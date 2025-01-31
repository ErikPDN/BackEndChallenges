package dev.erik.twitter_simplificado.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erik.twitter_simplificado.controllers.dtos.LoginRequestDTO;
import dev.erik.twitter_simplificado.controllers.dtos.LoginResponseDTO;
import dev.erik.twitter_simplificado.services.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenController {
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
    var token = this.tokenService.login(loginRequest.username(), loginRequest.password());
    var loginResponse = new LoginResponseDTO(token.token(), token.expiresIn());
    return ResponseEntity.ok(loginResponse);

  }
}
