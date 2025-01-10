package dev.erikneves.desafio_bancointer.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.erikneves.desafio_bancointer.domain.User;
import dev.erikneves.desafio_bancointer.repository.UserRepository;
import dev.erikneves.desafio_bancointer.service.UserService;
import dev.erikneves.desafio_bancointer.service.dto.UserDTO;
import dev.erikneves.desafio_bancointer.service.exceptions.EmailAlreadyExistsException;
import dev.erikneves.desafio_bancointer.service.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public UUID createUser(String name, String email) {
    var emailExists = this.userRepository.findByEmail(email);

    if (emailExists.isPresent()) {
      throw new EmailAlreadyExistsException();
    }

    var user = User.builder().name(name).email(email).build();
    this.userRepository.save(user);
    return user.getId();
  }

  @Override
  public UserDTO getUserById(UUID id) {
    var user = this.userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

    var userDTO = UserDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();

    return userDTO;
  }

  @Override
  public void deleteUserById(UUID id) {
    this.userRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void updateUserById(UUID userId, String name, String email) {
    var user = this.userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

    // Verificar se o novo email já existe para outro usuário
    var existingUserWithEmail = this.userRepository.findByEmail(email);
    if (existingUserWithEmail.isPresent() && !existingUserWithEmail.get().getId().equals(userId)) {
      throw new EmailAlreadyExistsException();
    }

    user.setName(name);
    user.setEmail(email);

    this.userRepository.save(user);
  }
}
