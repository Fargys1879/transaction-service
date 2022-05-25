package ru.fargys1879.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fargys1879.transactions.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> getByAccountId(Long accountId);
}
