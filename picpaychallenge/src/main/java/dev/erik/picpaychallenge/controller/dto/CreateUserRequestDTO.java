package dev.erik.picpaychallenge.controller.dto;

import dev.erik.picpaychallenge.entity.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequestDTO(
    @NotBlank(message = "Name is required") String fullName,
    @Email(message = "Invalid email format") String email,
    @NotBlank(message = "CPF/CNPJ is required") @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF/CNPJ must have 11 or 14 digits") String cpfCnpj,
    @NotBlank(message = "Password is required") String password,
    @NotNull(message = "User type is required") UserType userType) {
}
