package com.banksystem.bank.accounts.exception;

import java.math.BigDecimal;

public class InvalidAmountException extends RuntimeException {

  private InvalidAmountException(String message) {
    super(message);
  }

  public static InvalidAmountException notEnoughFunds(BigDecimal amount) {
    return new InvalidAmountException("Invalid amount: " + amount);
  }
}
