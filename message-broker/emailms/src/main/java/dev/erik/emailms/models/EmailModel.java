package dev.erik.emailms.models;

import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

import dev.erik.emailms.enums.StatusEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_email")
@Getter
@Setter
public class EmailModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID emailId;

  private UUID userId;

  private String emailFrom;

  private String emailTo;

  private String emailSubject;

  @Column(columnDefinition = "TEXT")
  private String emailBody;

  private LocalDateTime emailSentAt;

  private StatusEmail statusEmail;
}
