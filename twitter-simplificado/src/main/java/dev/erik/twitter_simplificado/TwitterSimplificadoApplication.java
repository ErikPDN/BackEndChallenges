package dev.erik.twitter_simplificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TwitterSimplificadoApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("USERNAME", dotenv.get("USERNAME"));
    System.setProperty("PASSWORD", dotenv.get("PASSWORD"));
    SpringApplication.run(TwitterSimplificadoApplication.class, args);
  }
}
