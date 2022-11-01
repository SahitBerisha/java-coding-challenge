package com.banksystem.security;

import com.nimbusds.jose.jwk.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

public class JwkSource {

  private JwkSource() {
  }

  public static RSAKey generateRsa() {
    var keyPair = KeyGeneratorUtils.generateRsaKey();
    var publicKey = (RSAPublicKey) keyPair.getPublic();
    var privateKey = (RSAPrivateKey) keyPair.getPrivate();
    return new RSAKey.Builder(publicKey)
        .privateKey(privateKey)
        .keyID(UUID.randomUUID().toString())
        .build();
  }
}
