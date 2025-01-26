package dev.erik.emailms.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import dev.erik.emailms.dto.EmailDTO;
import dev.erik.emailms.services.EmailService;

@Component
public class EmailConsumer {
  private EmailService emailService;

  public EmailConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  @RabbitListener(queues = "${broker.queue.email.name}")
  public void listenEmailQueue(@Payload EmailDTO dto) {
    System.out.println("EmailService: " + emailService);
    this.emailService.sendEmail(dto.emailTo(), dto.subject(), dto.text());
    System.out.println("Email sent");
  }
}
