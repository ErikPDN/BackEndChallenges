package dev.erik.emailms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@EnableRabbit
@SpringBootApplication
public class EmailmsApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
    System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
    SpringApplication.run(EmailmsApplication.class, args);
  }

}
