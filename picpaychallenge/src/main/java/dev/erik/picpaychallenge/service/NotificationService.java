package dev.erik.picpaychallenge.service;

import dev.erik.picpaychallenge.entity.Transaction;

public interface NotificationService {
  void sendNotification(Transaction transaction);
}
