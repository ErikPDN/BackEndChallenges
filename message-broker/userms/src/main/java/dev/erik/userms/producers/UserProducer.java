package dev.erik.userms.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import dev.erik.userms.services.dto.EmailDTO;

@Component
public class UserProducer {
  private final RabbitTemplate rabbitTemplate;

  public UserProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${broker.queue.email.name}")
  private String routingKey;

  public void publishMessageEmail(EmailDTO emailDTO) {
    try {
      System.out.println("Sending email message to broker: " + emailDTO);
      rabbitTemplate.convertAndSend("", this.routingKey, emailDTO);
      System.out.println("Message successfully sent to the broker.");
    } catch (Exception e) {
      System.err.println("Failed to send message to broker: " + e.getMessage());
    }
  }
}
