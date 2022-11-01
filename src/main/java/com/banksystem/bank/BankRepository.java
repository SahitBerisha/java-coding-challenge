package com.banksystem.bank;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BankRepository extends JpaRepository<Bank, String> {

  Optional<Bank> findBankByName(String name);
}
