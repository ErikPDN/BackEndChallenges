package dev.erik.spring.magalu_ms.controller.dto;

import dev.erik.spring.magalu_ms.domain.Channel;
import dev.erik.spring.magalu_ms.domain.Notification;
import dev.erik.spring.magalu_ms.domain.Status;

import java.time.LocalDateTime;

public record SchedulerNotificationDTO(
        LocalDateTime dateTime,
        String destination,
        String message,
        Channel.Values channel
) {

    public Notification toNotification() {
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}
