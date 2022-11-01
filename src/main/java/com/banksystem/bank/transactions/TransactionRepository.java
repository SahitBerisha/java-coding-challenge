package com.banksystem.bank.transactions;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TransactionRepository extends JpaRepository<Transaction, String> {

  @Query("SELECT t FROM Transaction t WHERE t.accountIdFrom = :id")
  List<Transaction> findAllByAccountId(@Param("id") String id);
}
