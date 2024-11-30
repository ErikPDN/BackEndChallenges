package dev.erik.spring.magalu_ms.controller.dto;

import dev.erik.spring.magalu_ms.domain.Channel;
import dev.erik.spring.magalu_ms.domain.Status;

import java.time.LocalDateTime;

public record ResponseNotificationDTO(
        LocalDateTime dateTime,
        String message,
        Channel channel,
        Status status) {
}
