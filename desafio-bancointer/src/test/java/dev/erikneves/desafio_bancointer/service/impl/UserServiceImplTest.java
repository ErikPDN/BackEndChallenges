package dev.erikneves.desafio_bancointer.service.impl;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.erikneves.desafio_bancointer.domain.User;
import dev.erikneves.desafio_bancointer.repository.UserRepository;
import dev.erikneves.desafio_bancointer.service.exceptions.EmailAlreadyExistsException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;

import java.util.Optional;

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
}
