package dev.erik.picpaychallenge.client.dto;

import java.math.BigDecimal;

public record TransactionNotificationDTO(Long payeeId, Long payerId, BigDecimal value) {
}
