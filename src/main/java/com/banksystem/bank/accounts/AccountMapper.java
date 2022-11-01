package com.banksystem.bank.accounts;

import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  public Account map(AccountRequest request) {
    var account = new Account();
    account.setUser(request.user());
    account.setBalance(request.balance());
    return account;
  }

  public AccountResponse map(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .user(account.getUser())
        .balance(account.getBalance())
        .build();
  }
}
