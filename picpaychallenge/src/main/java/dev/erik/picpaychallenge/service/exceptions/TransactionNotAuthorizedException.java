package dev.erik.picpaychallenge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransactionNotAuthorizedException extends PicPayException {

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);

    pb.setTitle("Transaction not authorized");
    pb.setDetail("Authorization service not authorized this transaction");

    return pb;
  }

}
