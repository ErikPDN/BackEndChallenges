package dev.erikneves.desafio_bancointer.controller;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.erikneves.desafio_bancointer.controller.dto.CreateUserRequestDTO;
import dev.erikneves.desafio_bancointer.controller.dto.UpdateUserRequestDTO;
import dev.erikneves.desafio_bancointer.service.UserService;
import dev.erikneves.desafio_bancointer.service.dto.UniqueDigitDTO;
import dev.erikneves.desafio_bancointer.service.dto.UserDTO;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {
  private UserController userController;

  @Mock
  private UserService userService;

  @BeforeAll
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userController = new UserController(this.userService);
  }

  @BeforeEach
  void resetMocks() {
    reset(this.userService);
  }

  @Test
  void itShouldCreateUser() {
    // given
    var uuid = UUID.randomUUID();
    var request = new CreateUserRequestDTO("John Doe", "john.doe@gmail.com");
    when(this.userService.createUser(request.name(), request.email()))
        .thenReturn(uuid);

    // when
    var response = userController.createUser(request);
    var body = response.getBody();

    // then
    assertThat(body.id()).isEqualTo(uuid);
    verify(this.userService, times(1)).createUser(request.name(), request.email());
  }

  @Test
  void isShouldGetUserById() {
    // given
    var uuid = UUID.randomUUID();
    var userDTO = new UserDTO(uuid, "John Doe", "john.doe@gmail.com");
    when(this.userService.getUserById(uuid))
        .thenReturn(userDTO);

    // when
    var response = this.userController.getUserById(uuid);
    var body = response.getBody();

    // then
    assertEquals(body.id(), userDTO.id());
    assertEquals(body.name(), userDTO.name());
    assertEquals(body.email(), userDTO.email());
  }

  @Test
  void isShouldDeleteUserById() {
    // given
    var uuid = UUID.randomUUID();

    // when
    this.userController.deleteUserById(uuid);

    // then
    verify(this.userService, times(1)).deleteUserById(uuid);
  }

  @Test
  void itShouldUpdateUserById() {
    // given
    var uuid = UUID.randomUUID();
    var request = new UpdateUserRequestDTO("John Doe", "john.doe@gmail.com");

    // when
    this.userController.updateUserById(uuid, request);

    // then
    verify(this.userService, times(1))
        .updateUserById(uuid, request.name(), request.email());
  }

  @Test
  void itShouldReturnAnListOfListOfUniqueDigitDTO() {
    // given
    var result = List.of(
        new UniqueDigitDTO("1", 1, 1),
        new UniqueDigitDTO("2", 2, 2),
        new UniqueDigitDTO("3", 3, 3));
    var uuid = UUID.randomUUID();
    when(this.userService.getCalculationsByUserId(uuid))
        .thenReturn(result);

    // when
    var response = this.userController.getCalculationsByUserId(uuid);
    var body = response.getBody();

    // then
    verify(this.userService, times(1))
        .getCalculationsByUserId(uuid);
    for (int i = 0; i < 3; i++) {
      assertEquals(body.uniqueDigits().get(i).number(), result.get(i).number());
      assertEquals(body.uniqueDigits().get(i).k(), result.get(i).k());
      assertEquals(body.uniqueDigits().get(i).result(), result.get(i).result());
    }
  }
}
