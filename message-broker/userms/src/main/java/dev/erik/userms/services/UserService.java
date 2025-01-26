package dev.erik.userms.services;

import dev.erik.userms.services.dto.UserDTO;

public interface UserService {
  UserDTO saveUser(String name, String email);

}
