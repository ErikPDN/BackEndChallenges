package dev.erik.twitter_simplificado.models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String content;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private Instant createdAt;
}
