package dev.erik.picpaychallenge.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateTransactionRequestDTO(
    @DecimalMin("0.01") @NotNull BigDecimal value,
    @NotNull Long payerId,
    @NotNull Long payeeId) {
}
