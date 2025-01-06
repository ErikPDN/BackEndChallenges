package dev.erik.picpaychallenge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class MerchantPaymentNotAllowedException extends PicPayException {

  private Long userId;

  public MerchantPaymentNotAllowedException(Long userId) {
    this.userId = userId;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Merchant payment not allowed");
    pb.setDetail("User with id " + userId + " is a merchant and cannot make payments");

    return pb;
  }
}
