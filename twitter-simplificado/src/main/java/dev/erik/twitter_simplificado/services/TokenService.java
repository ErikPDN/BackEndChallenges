package dev.erik.twitter_simplificado.services;

import dev.erik.twitter_simplificado.controllers.dtos.LoginResponseDTO;

public interface TokenService {
  LoginResponseDTO login(String username, String password);
}
