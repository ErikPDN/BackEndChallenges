package dev.erik.picpaychallenge.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.erik.picpaychallenge.service.exceptions.PicPayException;

//  responsável por capturar e tratar exceções lançadas em controladores de maneira centralizada. 
@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(PicPayException.class)
  public ProblemDetail handlePicPayException(PicPayException e) {
    return e.toProblemDetail();
  }
}
