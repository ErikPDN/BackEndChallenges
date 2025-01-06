package dev.erik.picpaychallenge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.erik.picpaychallenge.client.dto.TransactionNotificationDTO;

@FeignClient(name = "NotificationClient", url = "client.notification-service.url")
public interface NotificationClient {
  @PostMapping
  public ResponseEntity<Void> sendNotification(@RequestBody TransactionNotificationDTO trasaction);
}
