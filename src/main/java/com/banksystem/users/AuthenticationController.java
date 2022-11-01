package com.banksystem.users;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

  private final UserService service;

  @PostMapping("/token")
  public ResponseEntity<TokenResponse> getToken(@RequestBody UserRequest request) {
    log.debug("REST request to authenticate with username: {}" + request.username());
    return ResponseEntity.ok(service.login(request));
  }
}
