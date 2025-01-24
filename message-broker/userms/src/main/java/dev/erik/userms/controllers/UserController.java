package dev.erik.userms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erik.userms.controllers.dto.SaveUserRequestDTO;
import dev.erik.userms.controllers.dto.SaveUserResponseDTO;
import dev.erik.userms.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<SaveUserResponseDTO> saveUser(@RequestBody @Valid SaveUserRequestDTO user) {
    var userRequest = this.userService.saveUser(user.name(), user.email());
    var response = new SaveUserResponseDTO(userRequest.userId(), userRequest.name(), userRequest.email());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
