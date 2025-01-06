package dev.erik.picpaychallenge.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionResponseDTO(UUID id, BigDecimal value, Long payerId, Long payeeId) {
}
