package dev.erikneves.desafio_bancointer.controller.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetCalculationsByUserIdResponseDTO(
    @Schema(description = "Calculations of the user") List<UniqueDigitDTO> uniqueDigits) {
}
