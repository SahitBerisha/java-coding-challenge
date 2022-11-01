package com.banksystem.bank;

import static com.banksystem.core.FeeType.FLAT;
import static com.banksystem.core.FeeType.PERCENT;
import static java.util.stream.Collectors.toUnmodifiableList;

import com.banksystem.bank.accounts.AccountBalanceResponse;
import com.banksystem.bank.accounts.AccountMapper;
import com.banksystem.bank.accounts.AccountRequest;
import com.banksystem.bank.accounts.AccountResponse;
import com.banksystem.core.FeeType;
import com.banksystem.bank.transactions.Transaction;
import com.banksystem.bank.transactions.TransactionMapper;
import com.banksystem.bank.transactions.TransactionRepository;
import com.banksystem.bank.transactions.TransactionRequest;
import com.banksystem.bank.transactions.TransactionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankService {

  private final BankRepository repository;
  private final TransactionRepository transactionRepository;
  private final BankMapper mapper;
  private final AccountMapper accountMapper;
  private final TransactionMapper transactionMapper;


  @Transactional
  public BankResponse create(BankRequest request) {
    var bank = mapper.map(request);
    repository.save(bank);
    return mapper.map(bank);
  }

  @Transactional
  public AccountResponse create(String name, AccountRequest request) {
    var bank = repository.findBankByName(name).orElseThrow();
    var account = accountMapper.map(request);
    bank.addAccount(account);
    return accountMapper.map(account);
  }

  @Transactional
  public TransactionResponse create(String name, TransactionRequest request, FeeType feeType) {
    var bank = repository.findBankByName(name).orElseThrow();
    var transaction = transactionMapper.map(request);
    performTransaction(bank, transaction, feeType);
    transactionRepository.save(transaction);
    return transactionMapper.map(transaction);
  }

  @Transactional(readOnly = true)
  public List<TransactionResponse> getAllTransactionsByAccountId(String id) {
    return transactionRepository.findAllByAccountId(id).stream()
        .map(transactionMapper::map)
        .collect(toUnmodifiableList());
  }

  @Transactional(readOnly = true)
  public AccountBalanceResponse checkBalance(String name, String accountId) {
    var bank = repository.findBankByName(name).orElseThrow();
    var account = bank.getAccountById(accountId);
    return new AccountBalanceResponse(account.getUser(), account.getBalance());
  }

  @Transactional(readOnly = true)
  public List<AccountResponse> getAll(String name) {
    var bank = repository.findBankByName(name).orElseThrow();
    return bank.accounts.stream()
        .map(accountMapper::map)
        .collect(toUnmodifiableList());
  }

  @Transactional(readOnly = true)
  public TotalTransactionFeeResponse getTotalFeeAmount(String name) {
    var bank = repository.findBankByName(name).orElseThrow();
    var totalFeeAmount = bank.getTotalTransactionFeeAmount();
    return new TotalTransactionFeeResponse(totalFeeAmount);
  }

  @Transactional(readOnly = true)
  public TotalTransferResponse getTotalTransferAmount(String name) {
    var bank = repository.findBankByName(name).orElseThrow();
    var totalTransferAmount = bank.getTotalTransferAmount();
    return new TotalTransferResponse(totalTransferAmount);
  }

  private void performTransaction(Bank bank, Transaction transaction, FeeType feeType) {
    var amount = transaction.getAmount();
    var accountFrom = bank.getAccountById(transaction.getAccountIdFrom());
    var accountTo = bank.getAccountById(transaction.getAccountIdTo());
    var flatFee = bank.getTransactionFlatFeeAmount();
    var percentFee = bank.getTransactionPercentFeeValue();
    accountFrom.subtractFromBalance(amount);
    if (feeType.equals(FLAT)) {
      bank.addToTransactionFeeAmount(flatFee);
      transaction.calculateTransactionFee(flatFee, feeType);
    } else if (feeType.equals(PERCENT)) {
      bank.addToTransactionFeeAmount(transaction.calculatePercentFee(percentFee));
      transaction.calculateTransactionFee(percentFee, feeType);
    }
    bank.addToTransferAmount(transaction.getAmount());
    accountTo.addToBalance(transaction.getAmount());
  }
}
