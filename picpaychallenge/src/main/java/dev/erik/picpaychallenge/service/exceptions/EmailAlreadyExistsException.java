package dev.erik.picpaychallenge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EmailAlreadyExistsException extends PicPayException {
  private String detail;

  public EmailAlreadyExistsException(String detail) {
    this.detail = detail;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("EmailAlreadyExistsException");
    pb.setDetail(detail);

    return pb;
  }
}
