package dev.erik.picpaychallenge.service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(UUID id, BigDecimal value, Long payerId, Long payeeId) {
}
