package dev.erik.emailms.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import dev.erik.emailms.dto.EmailDTO;

@Component
public class EmailConsumer {

  @RabbitListener(queues = "${broker.queue.email.name}")
  public void listenEmailQueue(@Payload EmailDTO dto) {
    System.out.println("Email from " + dto.subject() + "to" + dto.emailTo());
  }
}
