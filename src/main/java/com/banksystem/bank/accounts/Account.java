package com.banksystem.bank.accounts;

import static java.math.BigDecimal.ZERO;

import com.banksystem.bank.Bank;
import com.banksystem.bank.accounts.exception.InvalidAmountException;
import com.banksystem.core.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = {"user"}))
public class Account extends AbstractEntity {

  @Column(name = "user", nullable = false)
  private String user;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bank_id")
  private Bank bank;

  @Transient
  public void addToBalance(BigDecimal amount) {
    this.balance = this.balance.add(amount);
  }

  @Transient
  public void subtractFromBalance(BigDecimal amount) {
    if (balance.subtract(amount).compareTo(ZERO) < 0) {
      throw InvalidAmountException.notEnoughFunds(amount);
    }
    this.balance = this.balance.subtract(amount);
  }
}
