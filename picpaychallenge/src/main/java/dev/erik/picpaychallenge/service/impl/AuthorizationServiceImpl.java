package dev.erik.picpaychallenge.service.impl;

import org.springframework.stereotype.Service;

import dev.erik.picpaychallenge.client.AuthorizationClient;
import dev.erik.picpaychallenge.service.AuthorizationService;
import dev.erik.picpaychallenge.service.exceptions.PicPayException;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

  private final AuthorizationClient authorizationClient;

  public AuthorizationServiceImpl(AuthorizationClient authorizationClient) {
    this.authorizationClient = authorizationClient;
  }

  @Override
  public boolean isAuthorize() {
    var response = this.authorizationClient.isAuthorize();

    if (response.getStatusCode().isError()) {
      throw new PicPayException();
    }

    return response.getBody().authorized();
  }
}
