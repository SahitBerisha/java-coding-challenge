package com.banksystem.bank.transactions;

import static com.banksystem.core.FeeType.FLAT;
import static com.banksystem.core.FeeType.PERCENT;
import static java.math.BigInteger.*;
import static java.math.RoundingMode.HALF_UP;

import com.banksystem.core.FeeType;
import com.banksystem.core.AbstractEntity;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction extends AbstractEntity {

  @Column(name = "amount", nullable = false)
  private BigDecimal amount = new BigDecimal(ZERO).setScale(2, HALF_UP);

  @Column(name = "account_from", nullable = false)
  private String accountIdFrom;

  @Column(name = "account_to", nullable = false)
  private String accountIdTo;

  @Column(name = "reason")
  private String reason;

  public void calculateTransactionFee(BigDecimal fee, FeeType feeType) {
    if (feeType.equals(PERCENT)) {
      this.amount = amount.subtract(amount.multiply(fee).divide(new BigDecimal(100), HALF_UP));
    } else if (feeType.equals(FLAT)) {
      this.amount = amount.subtract(fee);
    }
  }

  public BigDecimal calculatePercentFee(BigDecimal fee) {
   return fee.multiply(getAmount()).divide(new BigDecimal(100), HALF_UP);
  }
}
