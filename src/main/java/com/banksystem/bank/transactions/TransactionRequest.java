package com.banksystem.bank.transactions;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TransactionRequest(@NotNull BigDecimal amount,
                                 @NotBlank String accountIdFrom,
                                 @NotBlank String accountIdTo,
                                 String reason) {

}
