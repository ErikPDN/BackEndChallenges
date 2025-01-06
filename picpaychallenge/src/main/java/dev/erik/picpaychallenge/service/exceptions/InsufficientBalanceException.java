package dev.erik.picpaychallenge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicPayException {

  private Long userId;

  public InsufficientBalanceException(Long userId) {
    this.userId = userId;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Insufficient balance");
    pb.setDetail("User with id " + userId + " has insufficient balance");

    return pb;
  }

}
