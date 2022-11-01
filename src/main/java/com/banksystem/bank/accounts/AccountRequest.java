package com.banksystem.bank.accounts;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AccountRequest(@NotBlank String user, @NotNull BigDecimal balance) {

}
