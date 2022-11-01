package com.banksystem.bank;

import static org.springframework.http.HttpStatus.CREATED;

import com.banksystem.bank.accounts.AccountBalanceResponse;
import com.banksystem.bank.accounts.AccountRequest;
import com.banksystem.bank.accounts.AccountResponse;
import com.banksystem.bank.transactions.TransactionRequest;
import com.banksystem.bank.transactions.TransactionResponse;
import com.banksystem.core.FeeType;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banks")
public class BankController {

  private final BankService service;

  @PostMapping
  @Tag(name = "Bank")
  public ResponseEntity<BankResponse> create(@Valid @RequestBody BankRequest request) {
    log.debug("REST request to create a Bank : {}", request);
    return ResponseEntity.status(CREATED).body(service.create(request));
  }

  @Tag(name = "Account")
  @PostMapping("/{name}/accounts")
  public ResponseEntity<AccountResponse> create(@PathVariable String name,
                                                @Valid @RequestBody AccountRequest request) {
    log.debug("REST request to create an Account for Bank : {}, {}", name, request);
    return ResponseEntity.status(CREATED).body(service.create(name, request));
  }

  @Tag(name = "Transaction")
  @PostMapping("/{name}/transactions")
  public ResponseEntity<TransactionResponse> create(@PathVariable String name,
                                                    @Valid @RequestBody TransactionRequest request,
                                                    @RequestParam(value = "fee-type", required = true) FeeType feeType) {
    log.debug("REST request to create a Transaction for Bank : {}, {}", name, request);
    return ResponseEntity.status(CREATED).body(service.create(name, request, feeType));
  }

  @Tag(name = "Transaction")
  @GetMapping("/{name}/transactions")
  public ResponseEntity<List<TransactionResponse>> getAllByAccountId(@PathVariable String name,
                                                                     @RequestParam String id) {
    return ResponseEntity.ok(service.getAllTransactionsByAccountId(id));
  }

  @Tag(name = "Account")
  @GetMapping("/{name}/accounts/{id}/balance")
  public ResponseEntity<AccountBalanceResponse> checkAccountBalanceById(@PathVariable String name,
                                                                        @PathVariable String id) {
    log.debug("REST request to check balance for Account with id : {}", id);
    return ResponseEntity.ok(service.checkBalance(name, id));
  }

  @Tag(name = "Account")
  @GetMapping("/{name}/accounts")
  public ResponseEntity<List<AccountResponse>> getAll(@PathVariable String name) {
    log.debug("REST request to get all Accounts");
    return ResponseEntity.ok(service.getAll(name));
  }

  @Tag(name = "Transaction")
  @GetMapping("/{name}/transactions/total_fee")
  public ResponseEntity<TotalTransactionFeeResponse> getTotalTransactionFee(@PathVariable String name) {
    log.debug("REST request to get the total amount of Transactions fee");
    return ResponseEntity.ok(service.getTotalFeeAmount(name));
  }

  @Tag(name = "Transaction")
  @GetMapping("/{name}/transactions/total_transfer")
  public ResponseEntity<TotalTransferResponse> getTotalTransferFee(@PathVariable String name) {
    log.debug("REST request to get the total amount of transfers");
    return ResponseEntity.ok(service.getTotalTransferAmount(name));
  }

}
