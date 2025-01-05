package dev.erik.picpaychallenge.service;

import dev.erik.picpaychallenge.entity.UserType;

public interface UserService {
  Long createUser(String fullName, String email, String cpfCnpj, String password, UserType userType);
}
