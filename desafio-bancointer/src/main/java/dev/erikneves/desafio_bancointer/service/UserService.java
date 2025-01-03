package dev.erikneves.desafio_bancointer.service;

import java.util.UUID;

import dev.erikneves.desafio_bancointer.service.dto.UserDTO;

public interface UserService {
  UUID createUser(String name, String email);

  UserDTO getUserById(UUID id);
}
