package com.banksystem.bank;

import com.banksystem.bank.accounts.AccountResponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BankResponse {

  String id;
  String name;
  BigDecimal totalTransactionFeeAmount;
  BigDecimal totalTransferAmount;
  BigDecimal transactionFlatFeeAmount;
  BigDecimal transactionPercentFeeValue;
  List<AccountResponse> accounts;
}

record TotalTransactionFeeResponse(BigDecimal totalTransactionFeeAmount) {

}

record TotalTransferResponse(BigDecimal totalTransferAmount) {

}
