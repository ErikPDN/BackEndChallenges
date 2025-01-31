package dev.erik.twitter_simplificado.services.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.erik.twitter_simplificado.repositories.RoleRepository;
import dev.erik.twitter_simplificado.repositories.UserRepository;
import dev.erik.twitter_simplificado.services.UserService;
import dev.erik.twitter_simplificado.services.exceptions.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import dev.erik.twitter_simplificado.models.User;
import dev.erik.twitter_simplificado.controllers.dtos.GetUsersResponseDTO;
import dev.erik.twitter_simplificado.controllers.dtos.UserResponseDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  @Override
  @Transactional
  public void createUser(String username, String password) {
    this.userRepository.findByUsername(username).ifPresent(user -> {
      throw new UserAlreadyExistsException("Username already exists");
    });

    var basicRole = this.roleRepository.findByName("BASIC").orElseThrow();

    var user = new User();
    user.setUsername(username);
    user.setPassword(this.passwordEncoder.encode(password));
    user.setRoles(Set.of(basicRole));

    this.userRepository.save(user);
  }

  @Override
  public GetUsersResponseDTO getUsers() {
    var users = this.userRepository.findAll();
    var usersDTO = users.stream().map(user -> new UserResponseDTO(user.getUserId(), user.getUsername()))
        .collect(Collectors.toList());
    return new GetUsersResponseDTO(usersDTO);
  }
}
