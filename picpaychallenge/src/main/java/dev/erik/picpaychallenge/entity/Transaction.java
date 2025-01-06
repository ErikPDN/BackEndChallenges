package dev.erik.picpaychallenge.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "transactions")
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User senderUser;

  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User receiverUser;

  private BigDecimal value;

  private LocalDateTime timestamp;

  public Transaction(User senderUser, User receiverUser, BigDecimal value) {
    this.senderUser = senderUser;
    this.receiverUser = receiverUser;
    this.value = value;
    this.timestamp = LocalDateTime.now();
  }
}
