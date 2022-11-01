package com.banksystem.bank;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;

public record BankRequest(@NotBlank String name,
                          BigDecimal transactionFlatFeeAmount,
                          BigDecimal transactionPercentFeeValue) {

}
