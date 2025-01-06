package dev.erik.picpaychallenge.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullName;

  @Column(unique = true)
  private String cpfCnpj;

  @Column(unique = true)
  private String email;

  private String password;

  private BigDecimal balance = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  public void credit(BigDecimal value) {
    this.balance = this.balance.add(value);
  }

  public void debit(BigDecimal value) {
    this.balance = this.balance.subtract(value);
  }
}
