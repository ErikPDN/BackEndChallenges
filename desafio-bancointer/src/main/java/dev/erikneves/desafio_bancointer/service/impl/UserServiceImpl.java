package dev.erikneves.desafio_bancointer.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.erikneves.desafio_bancointer.domain.User;
import dev.erikneves.desafio_bancointer.repository.UserRepository;
import dev.erikneves.desafio_bancointer.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public UUID createUser(String name, String email) {
    var user = User.builder().name(name).email(email).build();
    this.userRepository.save(user);
    return user.getId();
  }
}
