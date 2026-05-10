package org.example.repository;

import org.example.model.Transaction;

import java.util.List;

public interface TransactionRepository
        extends Repository<Transaction, Long> {

    List<Transaction> findByUserUserIdAndDeletedFalse(String userId);
}