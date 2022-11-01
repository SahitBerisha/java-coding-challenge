package com.banksystem.bank.accounts;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountResponse {

  String id;
  String user;
  BigDecimal balance;
}
