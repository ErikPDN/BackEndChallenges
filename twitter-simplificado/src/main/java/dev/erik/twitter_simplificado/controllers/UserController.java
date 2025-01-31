package dev.erik.twitter_simplificado.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import dev.erik.twitter_simplificado.controllers.dtos.CreateUserRequestDTO;
import dev.erik.twitter_simplificado.services.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody CreateUserRequestDTO userRequest) {
    this.userService.createUser(userRequest.username(), userRequest.password());
    log.info("Usuário criado: {}", userRequest.username());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
