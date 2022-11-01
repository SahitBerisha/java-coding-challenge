package com.banksystem.bank.transactions;

import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public Transaction map(TransactionRequest request) {
    var transaction = new Transaction();
    transaction.setAmount(request.amount());
    transaction.setAccountIdFrom(request.accountIdFrom());
    transaction.setAccountIdTo(request.accountIdTo());
    transaction.setReason(request.reason());
    return transaction;
  }

  public TransactionResponse map(Transaction transaction) {
    return TransactionResponse.builder()
        .id(transaction.getId())
        .amount(transaction.getAmount())
        .from(transaction.getAccountIdFrom())
        .to(transaction.getAccountIdTo())
        .reason(transaction.getReason())
        .build();
  }
}
