package dev.erik.picpaychallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PicpaychallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(PicpaychallengeApplication.class, args);
  }

}
