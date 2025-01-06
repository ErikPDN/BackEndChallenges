package dev.erik.picpaychallenge.service;

import dev.erik.picpaychallenge.service.dto.TransactionDTO;

public interface NotificationService {
  void sendNotification(TransactionDTO transaction);
}
