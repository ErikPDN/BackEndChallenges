package dev.erik.emailms.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
  @Value("${broker.queue.email.name}")
  private String name;

  @Bean
  public Queue queue() {
    return new Queue(name, true);
  }

  @Bean
  public Jackson2JsonMessageConverter converter() {
    ObjectMapper mapper = new ObjectMapper();
    return new Jackson2JsonMessageConverter(mapper);
  }
}
