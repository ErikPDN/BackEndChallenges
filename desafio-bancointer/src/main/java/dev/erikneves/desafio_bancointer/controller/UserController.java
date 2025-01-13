package dev.erikneves.desafio_bancointer.controller;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erikneves.desafio_bancointer.controller.dto.CreateUserRequestDTO;
import dev.erikneves.desafio_bancointer.controller.dto.CreateUserResponseDTO;
import dev.erikneves.desafio_bancointer.controller.dto.GetCalculationsByUserIdResponseDTO;
import dev.erikneves.desafio_bancointer.controller.dto.GetUserByIdResponseDTO;
import dev.erikneves.desafio_bancointer.controller.dto.UniqueDigitResponseDTO;
import dev.erikneves.desafio_bancointer.controller.dto.UpdateUserRequestDTO;
import dev.erikneves.desafio_bancointer.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<CreateUserResponseDTO> createUser(
      @RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {

    var createUserID = this.userService.createUser(createUserRequestDTO.name(), createUserRequestDTO.email());
    var response = new CreateUserResponseDTO(createUserID);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetUserByIdResponseDTO> getUserById(@PathVariable UUID id) {
    var userDTO = this.userService.getUserById(id);
    var response = new GetUserByIdResponseDTO(userDTO.id(), userDTO.name(), userDTO.email());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
    this.userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUserById(@PathVariable UUID id,
      @RequestBody @Valid UpdateUserRequestDTO updateUserRequestDTO) {

    this.userService.updateUserById(id,
        updateUserRequestDTO.name(),
        updateUserRequestDTO.email());
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/calculations")
  public ResponseEntity<GetCalculationsByUserIdResponseDTO> getCalculationsByUserId(@PathVariable UUID id) {
    var listOfUniqueDigit = this.userService.getCalculationsByUserId(id);

    var listOfUniqueDigitDTO = listOfUniqueDigit
        .stream()
        .map(uniqueDigitDTO -> new UniqueDigitResponseDTO(uniqueDigitDTO.number(),
            uniqueDigitDTO.k(),
            uniqueDigitDTO.result()))
        .collect(Collectors.toList());
    var response = new GetCalculationsByUserIdResponseDTO(listOfUniqueDigitDTO);
    return ResponseEntity.ok(response);
  }
}
