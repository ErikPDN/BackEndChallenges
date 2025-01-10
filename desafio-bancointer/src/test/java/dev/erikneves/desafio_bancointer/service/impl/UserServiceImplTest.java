package dev.erikneves.desafio_bancointer.service.impl;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.erikneves.desafio_bancointer.domain.User;
import dev.erikneves.desafio_bancointer.repository.UserRepository;
import dev.erikneves.desafio_bancointer.service.exceptions.EmailAlreadyExistsException;
import dev.erikneves.desafio_bancointer.service.exceptions.UserNotFoundException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
class UserServiceImplTest {
  private UserServiceImpl userServiceImpl;
  @Mock
  private UserRepository userRepository;

  @BeforeAll
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.userServiceImpl = new UserServiceImpl(this.userRepository);
  }

  @BeforeEach
  void resetMocks() {
    reset(this.userRepository);
  }

  @Test
  void itShouldCreateAnUserAndSaveOnRepository() {
    // given
    var email = "john.doe@gmail.com";
    var name = "John Doe";

    when(this.userRepository.findByEmail(email))
        .thenReturn(Optional.empty());

    // when
    this.userServiceImpl.createUser(name, email);

    // then
    var captor = ArgumentCaptor.forClass(User.class);
    verify(this.userRepository, times(1)).save(captor.capture());

    var user = captor.getValue();
    assertEquals(email, user.getEmail());
    assertEquals(name, user.getName());
  }

  @Test
  void itShouldThrowAnErrorIfEmailAlreadyExists() {
    // given
    var email = "john.doe@gmail.com";
    var name = "John Doe";

    when(this.userRepository.findByEmail(email))
        .thenReturn(Optional.of(User.builder().build()));

    // when & then
    assertThrows(EmailAlreadyExistsException.class, () -> {
      this.userServiceImpl.createUser(name, email);
    });
    verify(this.userRepository, times(0)).save(any());
  }

  @Test
  void itShouldReturnAnUserDTOIfUserExists() {
    // given
    UUID uuid = UUID.randomUUID();
    var user = User.builder().id(uuid).name("John Doe").email("john.doe@gmail.com").build();
    when(this.userRepository.findById(uuid))
        .thenReturn(Optional.of(user));

    // when
    var userDTO = this.userServiceImpl.getUserById(uuid);

    // then
    assertEquals(uuid, userDTO.id());
    assertEquals(user.getName(), userDTO.name());
    assertEquals(user.getEmail(), userDTO.email());
    verify(this.userRepository, times(1))
        .findById(uuid);
  }

  @Test
  void itShouldReturnAnErrorIfUserDoesntExist() {
    // given
    UUID uuid = UUID.randomUUID();
    when(this.userRepository.findById(uuid))
        .thenReturn(Optional.empty());

    // when & then
    assertThrows(UserNotFoundException.class, () -> {
      this.userServiceImpl.getUserById(uuid);
    });
    verify(this.userRepository, times(1))
        .findById(uuid);
  }

  @Test
  void isShouldDeleteUserIfExists() {
    // given
    UUID uuid = UUID.randomUUID();

    // when
    this.userServiceImpl.deleteUserById(uuid);

    // then
    verify(this.userRepository, times(1))
        .deleteById(uuid);
  }

  @Test
  void itShouldUpdateUser() {
    // given
    var uuid = UUID.randomUUID();
    var user = User.builder().id(uuid).name("John Doe").email("john.doe@gmail.com").build();
    when(this.userRepository.findById(uuid))
        .thenReturn(Optional.of(user));

    // when
    this.userServiceImpl.updateUserById(uuid, "Jane Doe", "jane.doe@gmail.com");

    // then
    var captor = ArgumentCaptor.forClass(User.class);
    verify(this.userRepository, times(1))
        .save(captor.capture());

    var updatedUser = captor.getValue();
    assertEquals(uuid, updatedUser.getId());
    assertEquals("Jane Doe", updatedUser.getName());
    assertEquals("jane.doe@gmail.com", updatedUser.getEmail());
  }

  @Test
  void itShouldThrowAnErrorIfUserDoesnotExistWhenUpdating() {
    // given
    var uuid = UUID.randomUUID();
    when(this.userRepository.findById(uuid))
        .thenReturn(Optional.empty());

    // when & then
    assertThrows(UserNotFoundException.class, () -> {
      this.userServiceImpl.updateUserById(uuid, "Jane Doe", "jane.doe@gmail.com");
    });
    verify(this.userRepository, times(0))
        .save(any());

  }
}
