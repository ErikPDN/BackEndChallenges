package dev.erik.userms.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.erik.userms.models.UserModel;
import dev.erik.userms.producers.UserProducer;
import dev.erik.userms.repositories.UserRepository;
import dev.erik.userms.services.UserService;
import dev.erik.userms.services.dto.UserDTO;
import dev.erik.userms.services.dto.EmailDTO;
import dev.erik.userms.services.exceptions.EmailAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserProducer userProducer;

  public UserServiceImpl(UserRepository userRepository, UserProducer userProducer) {
    this.userRepository = userRepository;
    this.userProducer = userProducer;
  }

  @Override
  @Transactional
  public UserDTO saveUser(String name, String email) {
    this.userRepository.findByEmail(email).ifPresent(user -> {
      throw new EmailAlreadyExistsException("Email already exists");
    });

    var user = new UserModel(name, email);
    this.userRepository.save(user);

    this.publishUserCreatedEvent(user.getId(), name, email);

    return new UserDTO(
        user.getId(),
        user.getName(),
        user.getEmail());
  }

  private void publishUserCreatedEvent(UUID userId, String name, String email) {
    var emailDTO = new EmailDTO(
        userId,
        email,
        "Cadastro com sucesso",
        name + ", seu cadastro foi realizado com sucesso!");
    this.userProducer.publishMessageEmail(emailDTO);
  }
}
