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

    public Transaction create(User user, String description, Double amount, String type) {

        Transaction t = new TransactionBuilder()
                .setUser(user)
                .setDescription(description)
                .setAmount(amount)
                .setType(type)
                .build();

        return repo.save(t);
    }

    public List<Transaction> getUserTransactions(String userId) {
        return repo.findByUserUserIdAndDeletedFalse(userId);
    }

    public Transaction updateAmount(Long id, Double amount) {

        Transaction t = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Transaction not found")
        );

        t.setAmount(amount);
        return repo.save(t);
    }

    public void softDelete(Long id) {

        Transaction t = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Transaction not found")
        );

        t.setDeleted(true);
        repo.save(t);
    }
}