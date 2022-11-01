package com.banksystem.bank.accounts;

import java.math.BigDecimal;

public record AccountBalanceResponse(String user, BigDecimal balance) {

}
