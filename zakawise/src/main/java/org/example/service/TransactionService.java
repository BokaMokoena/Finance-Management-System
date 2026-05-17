package org.example.service;

import org.example.creational_patterns.TransactionBuilder;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction create(User user,
                              String description,
                              Double amount,
                              String type) {

        Transaction transaction =
                new TransactionBuilder()
                        .setUser(user)
                        .setDescription(description)
                        .setAmount(amount)
                        .setType(type)
                        .build();

        repo.save(transaction);

        return transaction;
    }

    public Transaction get(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaction not found with id: " + id));
    }

    public List<Transaction> getUserTransactions(String userId) {

        return repo.findByUserUserIdAndDeletedFalse(userId);
    }

    public Transaction updateAmount(Long id, Double amount) {

        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        Transaction transaction = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaction not found with id: " + id));

        transaction.setAmount(amount);

        repo.save(transaction);

        return transaction;
    }

    public void delete(Long id) {

        if (!repo.findById(id).isPresent()) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }

        repo.delete(id);
    }
}