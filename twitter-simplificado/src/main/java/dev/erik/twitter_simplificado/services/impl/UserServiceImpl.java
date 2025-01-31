package dev.erik.twitter_simplificado.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.erik.twitter_simplificado.repositories.UserRepository;
import dev.erik.twitter_simplificado.services.UserService;
import dev.erik.twitter_simplificado.services.exceptions.UserAlreadyExistsException;
import dev.erik.twitter_simplificado.models.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public void createUser(String username, String password) {
    this.userRepository.findByUsername(username).ifPresent(user -> {
      throw new UserAlreadyExistsException("Username already exists");
    });

    var user = new User();
    user.setUsername(username);
    user.setPassword(this.passwordEncoder.encode(password));

    this.userRepository.save(user);
  }
}
