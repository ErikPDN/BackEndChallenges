package dev.erik.userms.services;

import dev.erik.userms.services.dto.UserDTO;

public interface UserService {
  public UserDTO saveUser(String name, String email);
}
