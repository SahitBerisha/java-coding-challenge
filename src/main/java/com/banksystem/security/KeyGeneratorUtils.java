package com.banksystem.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import org.springframework.stereotype.Component;

@Component
public class KeyGeneratorUtils {

  private KeyGeneratorUtils() {
  }

  static KeyPair generateRsaKey() {
    KeyPair keyPair;
    try {
      var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      keyPair = keyPairGenerator.generateKeyPair();
    } catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
    return keyPair;
  }
}
