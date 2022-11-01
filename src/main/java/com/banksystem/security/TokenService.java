package com.banksystem.security;

import static java.time.temporal.ChronoUnit.HOURS;

import java.time.Instant;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final JwtEncoder encoder;

  public String generateToken(Authentication authentication) {
    var now = Instant.now();
    var scope = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
    var claims = JwtClaimsSet.builder()
        .issuer("bank")
        .issuedAt(now)
        .expiresAt(now.plus(1, HOURS))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();
    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

  }
}
