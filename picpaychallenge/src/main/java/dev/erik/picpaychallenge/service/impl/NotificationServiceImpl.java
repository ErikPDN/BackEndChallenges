package dev.erik.picpaychallenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.erik.picpaychallenge.client.NotificationClient;
import dev.erik.picpaychallenge.client.dto.TransactionNotificationDTO;
import dev.erik.picpaychallenge.service.NotificationService;
import dev.erik.picpaychallenge.service.dto.TransactionDTO;

@Service
public class NotificationServiceImpl implements NotificationService {

  private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

  private final NotificationClient notificationClient;

  public NotificationServiceImpl(NotificationClient notificationClient) {
    this.notificationClient = notificationClient;
  }

  @Override
  public void sendNotification(TransactionDTO transaction) {

    try {
      logger.info("Sending notification for transaction: {}", transaction);

      var notificationDTO = new TransactionNotificationDTO(transaction.payeeId(), transaction.payerId(),
          transaction.value());

      var response = this.notificationClient.sendNotification(notificationDTO);

      if (response.getStatusCode().isError()) {
        logger.error("Error sending notification: {}", response);
      }

    } catch (Exception e) {
      logger.error("Error sending notification", e);
    }
  }
}
