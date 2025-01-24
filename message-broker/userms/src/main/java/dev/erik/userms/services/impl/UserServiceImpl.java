package dev.erik.userms.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.erik.userms.models.UserModel;
import dev.erik.userms.repositories.UserRepository;
import dev.erik.userms.services.UserService;
import dev.erik.userms.services.dto.UserDTO;
import dev.erik.userms.services.exceptions.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDTO saveUser(String name, String email) {
    this.userRepository.findByEmail(email).ifPresent(user -> {
      throw new EmailAlreadyExistsException("Email already exists");
    });

    var user = new UserModel(name, email);
    this.userRepository.save(user);

    return new UserDTO(
        user.getId(),
        user.getName(),
        user.getEmail());
  }
}
