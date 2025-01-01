package dev.erikneves.desafio_bancointer.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erikneves.desafio_bancointer.controller.dto.CreateUserRequestDTO;
import dev.erikneves.desafio_bancointer.controller.dto.CreateUserResponseDTO;
import dev.erikneves.desafio_bancointer.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<CreateUserResponseDTO> createUser(
      @RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {

    var createUserID = this.userService.createUser(createUserRequestDTO.name(), createUserRequestDTO.email());
    var response = new CreateUserResponseDTO(createUserID);
    return ResponseEntity.ok(response);
  }
}
