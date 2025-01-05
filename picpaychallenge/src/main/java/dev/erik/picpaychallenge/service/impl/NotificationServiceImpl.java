package dev.erik.picpaychallenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.erik.picpaychallenge.client.NotificationClient;
import dev.erik.picpaychallenge.entity.Transaction;
import dev.erik.picpaychallenge.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

  private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

  private final NotificationClient notificationClient;

  public NotificationServiceImpl(NotificationClient notificationClient) {
    this.notificationClient = notificationClient;
  }

  @Override
  public void sendNotification(Transaction transaction) {

    try {
      logger.info("Sending notification for transaction: {}", transaction);
      var response = this.notificationClient.sendNotification(transaction);

      if (response.getStatusCode().isError()) {
        logger.error("Error sending notification: {}", response);
      }

    } catch (Exception e) {
      logger.error("Error sending notification", e);
    }
  }
}
