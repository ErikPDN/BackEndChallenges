package dev.erik.picpaychallenge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import dev.erik.picpaychallenge.client.dto.AuthorizationResponseDTO;

@FeignClient(name = "AuthorizationClient", url = "${client.authorization-service.url}")
public interface AuthorizationClient {

  @GetMapping()
  public ResponseEntity<AuthorizationResponseDTO> isAuthorize();
}
