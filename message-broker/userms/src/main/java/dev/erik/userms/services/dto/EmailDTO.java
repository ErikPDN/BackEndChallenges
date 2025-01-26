package dev.erik.userms.services.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private UUID userId;
  private String emailTo;
  private String subject;
  private String text;
}
