package dev.erikneves.desafio_bancointer.service;

import java.util.UUID;

public interface UserService {
  UUID createUser(String name, String email);
}
