package com.banksystem.bank.transactions;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TransactionResponse {

  String id;
  BigDecimal amount;
  String from;
  String to;
  String reason;
}
