package dev.erik.twitter_simplificado.models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tweets")
@RequiredArgsConstructor
@Getter
@Setter
public class Tweet {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long tweetId;

  private User user;

  private String content;

  @CreationTimestamp
  private Instant createdAt;
}
