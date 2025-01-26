package dev.erik.emailms.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import dev.erik.emailms.enums.StatusEmail;
import dev.erik.emailms.models.EmailModel;
import dev.erik.emailms.repositories.EmailRepository;
import dev.erik.emailms.services.EmailService;
import jakarta.transaction.Transactional;

@Service
public class EmailServiceImpl implements EmailService {
  private final EmailRepository emailRepository;
  private final JavaMailSender javaMailSender;

  public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender javaMailSender) {
    this.emailRepository = emailRepository;
    this.javaMailSender = javaMailSender;
  }

  @Value(value = "${spring.mail.username}")
  private String emailFrom;

  @Transactional
  public void sendEmail(String to, String subject, String body) {

    var emailModel = new EmailModel();
    emailModel.setEmailSentAt(LocalDateTime.now());
    emailModel.setEmailFrom(emailFrom);
    emailModel.setEmailTo(to);
    emailModel.setEmailSubject(subject);
    emailModel.setEmailBody(body);

    try {
      sendSimpleMail(to, subject, body);
      emailModel.setStatusEmail(StatusEmail.SENT);
    } catch (MailException e) {
      emailModel.setStatusEmail(StatusEmail.ERROR);
    }

    this.emailRepository.save(emailModel);
  }

  private void sendSimpleMail(String to, String subject, String body) {
    var simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(emailFrom);
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(body);

    javaMailSender.send(simpleMailMessage);
  }
}
