package com.banksystem.users;

import com.banksystem.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final DaoAuthenticationProvider authenticationManager;
  private final TokenService tokenService;

  @Transactional(readOnly = true)
  public TokenResponse login(UserRequest request) {
    var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    var token = tokenService.generateToken(authentication);
    return new TokenResponse(request.username(), token);
  }
}
