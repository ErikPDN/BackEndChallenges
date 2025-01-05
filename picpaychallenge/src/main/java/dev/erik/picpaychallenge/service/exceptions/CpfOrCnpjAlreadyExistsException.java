package dev.erik.picpaychallenge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CpfOrCnpjAlreadyExistsException extends PicPayException {
  private String detail;

  public CpfOrCnpjAlreadyExistsException(String detail) {
    this.detail = detail;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("CPF or CNPJ already exists");
    pb.setDetail(detail);

    return pb;
  }
}
