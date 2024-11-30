package dev.erik.spring.magalu_ms.controller;

import dev.erik.spring.magalu_ms.controller.dto.ResponseNotificationDTO;
import dev.erik.spring.magalu_ms.controller.dto.SchedulerNotificationDTO;
import dev.erik.spring.magalu_ms.domain.Notification;
import dev.erik.spring.magalu_ms.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> schedulerNotification(@RequestBody SchedulerNotificationDTO dto) {
        notificationService.scheduleNotification(dto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<ResponseNotificationDTO> consultNotification(@PathVariable("notificationId") Long notificationId) {
        var notification = notificationService.findById(notificationId);


        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = toResponseDTO(notification.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> cancelNotification(@PathVariable("notificationId") Long notificationId) {
        notificationService.cancelNotification(notificationId);

        return ResponseEntity.noContent().build();
    }


    private ResponseNotificationDTO toResponseDTO(Notification notification) {
        return new ResponseNotificationDTO(
                notification.getDateTime(),
                notification.getMessage(),
                notification.getChannel(),
                notification.getStatus()
        );
    }

}
