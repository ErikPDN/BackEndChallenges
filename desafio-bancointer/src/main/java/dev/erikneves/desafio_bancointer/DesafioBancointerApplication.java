package dev.erikneves.desafio_bancointer;

import java.math.BigInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.erikneves.desafio_bancointer.domain.UniqueDigit;

@SpringBootApplication
public class DesafioBancointerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DesafioBancointerApplication.class, args);
    var uniqueDigit = new UniqueDigit(new BigInteger("9875"), 4);
    System.out.println("Resultado: " + uniqueDigit.getResult());
  }

}
