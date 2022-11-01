package com.banksystem.bank;

import com.banksystem.bank.accounts.Account;
import com.banksystem.bank.accounts.exception.AccountNotFound;
import com.banksystem.core.AbstractEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "banks")
public class Bank extends AbstractEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Account> accounts = new ArrayList<>();

  @Column(name = "total_transaction_fee_amount")
  private BigDecimal totalTransactionFeeAmount;

  @Column(name = "total_transfer_amount")
  private BigDecimal totalTransferAmount;

  @Column(name = "transaction_flat_fee_amount", nullable = false)
  private BigDecimal transactionFlatFeeAmount;

  @Column(name = "transaction_percent_fee_value", nullable = false)
  private BigDecimal transactionPercentFeeValue;

  @Transient
  public void addAccount(Account account) {
    account.setBank(this);
    this.accounts.add(account);
  }

  @Transient
  public Account getAccountById(String id) {
    return this.accounts.stream()
        .filter(account -> account.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> AccountNotFound.withId(id));
  }

  @Transient
  public void addToTransferAmount(BigDecimal amount) {
    this.totalTransferAmount = this.totalTransferAmount.add(amount);
  }

  @Transient
  public void addToTransactionFeeAmount(BigDecimal amount) {
    this.totalTransactionFeeAmount = this.totalTransactionFeeAmount.add(amount);
  }
}
