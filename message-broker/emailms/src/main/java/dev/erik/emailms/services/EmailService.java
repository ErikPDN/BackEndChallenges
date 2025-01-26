package dev.erik.emailms.services;

public interface EmailService {
  void sendEmail(String to, String subject, String body);
}
