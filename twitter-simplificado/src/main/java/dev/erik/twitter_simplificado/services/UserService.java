package dev.erik.twitter_simplificado.services;

import dev.erik.twitter_simplificado.controllers.dtos.GetUsersResponseDTO;

public interface UserService {
  void createUser(String username, String password);

  GetUsersResponseDTO getUsers();
}
