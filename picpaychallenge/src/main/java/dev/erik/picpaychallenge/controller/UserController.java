package dev.erik.picpaychallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erik.picpaychallenge.controller.dto.CreateUserRequestDTO;
import dev.erik.picpaychallenge.controller.dto.CreateUserResponseDTO;
import dev.erik.picpaychallenge.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid CreateUserRequestDTO userDTO) {
    var createUserId = this.userService.createUser(userDTO.fullName(), userDTO.email(), userDTO.cpfCnpj(),
        userDTO.password(), userDTO.userType());

    var response = new CreateUserResponseDTO(createUserId);
    return ResponseEntity.ok(response);
  }
}
