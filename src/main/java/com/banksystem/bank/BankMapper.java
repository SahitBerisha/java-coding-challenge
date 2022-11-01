package com.banksystem.bank;

import static java.util.stream.Collectors.toUnmodifiableList;

import com.banksystem.bank.accounts.AccountMapper;
import com.banksystem.bank.accounts.AccountResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankMapper {

  private final AccountMapper accountMapper;

  public Bank map(BankRequest request) {
    var bank = new Bank();
    bank.setName(request.name());
    bank.setTransactionFlatFeeAmount(request.transactionFlatFeeAmount());
    bank.setTransactionPercentFeeValue(request.transactionPercentFeeValue());
    return bank;
  }

  public BankResponse map(Bank bank) {
    return BankResponse.builder()
        .id(bank.getId())
        .name(bank.getName())
        .totalTransactionFeeAmount(bank.getTotalTransactionFeeAmount())
        .totalTransferAmount(bank.getTotalTransferAmount())
        .transactionFlatFeeAmount(bank.getTransactionFlatFeeAmount())
        .transactionPercentFeeValue(bank.getTransactionPercentFeeValue())
        .accounts(accounts(bank))
        .build();
  }

  private List<AccountResponse> accounts(Bank bank) {
    return bank.accounts.stream()
        .map(accountMapper::map)
        .collect(toUnmodifiableList());
  }
}
