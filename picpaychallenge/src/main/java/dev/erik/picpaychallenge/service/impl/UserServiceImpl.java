package dev.erik.picpaychallenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.erik.picpaychallenge.entity.User;
import dev.erik.picpaychallenge.entity.UserType;
import dev.erik.picpaychallenge.repository.UserRepository;
import dev.erik.picpaychallenge.service.UserService;
import dev.erik.picpaychallenge.service.exceptions.CpfOrCnpjAlreadyExistsException;
import dev.erik.picpaychallenge.service.exceptions.EmailAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Long createUser(String fullName, String email, String cpfCnpj, String password, UserType userType) {
    // Validação de duplicidade
    checkIfUserExists(email, cpfCnpj);

    // Criptografar senha
    // String encryptedPassword = passwordEncoder.encode(password);

    var user = User.builder()
        .fullName(fullName)
        .email(email)
        .cpfCnpj(cpfCnpj)
        .password(password)
        .userType(userType)
        .build();

    this.userRepository.save(user);

    return user.getId();
  }

  private void checkIfUserExists(String email, String cpfCnpj) {
    if (this.userRepository.findByEmail(email).isPresent()) {
      throw new EmailAlreadyExistsException("Email already exists");
    }

    if (this.userRepository.findByCpfCnpj(cpfCnpj).isPresent()) {
      throw new CpfOrCnpjAlreadyExistsException("Cpf or Cnpj already exists");
    }
  }
}
