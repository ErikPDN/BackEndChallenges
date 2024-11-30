package dev.erik.spring.magalu_ms.repository;

import dev.erik.spring.magalu_ms.domain.Notification;
import dev.erik.spring.magalu_ms.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
}
