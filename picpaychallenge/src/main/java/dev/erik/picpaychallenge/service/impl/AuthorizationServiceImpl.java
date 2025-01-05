package dev.erik.picpaychallenge.service.impl;

import dev.erik.picpaychallenge.client.AuthorizationClient;
import dev.erik.picpaychallenge.service.AuthorizationService;
import dev.erik.picpaychallenge.service.exceptions.PicPayException;

public class AuthorizationServiceImpl implements AuthorizationService {

  private final AuthorizationClient authorizationClient;

  public AuthorizationServiceImpl(AuthorizationClient authorizationClient) {
    this.authorizationClient = authorizationClient;
  }

  @Override
  public boolean isAuthorize() {
    var response = authorizationClient.isAuthorize();

    if (response.getStatusCode().isError()) {
      throw new PicPayException();
    }

    return false;
  }
}
