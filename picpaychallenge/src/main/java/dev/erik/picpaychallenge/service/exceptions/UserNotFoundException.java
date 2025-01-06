package dev.erik.picpaychallenge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends PicPayException {

  private Long userId;

  public UserNotFoundException(Long userId) {
    this.userId = userId;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("User not found");
    pb.setDetail("User with id " + userId + " not found");

    return pb;
  }

}
