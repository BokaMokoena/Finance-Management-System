package org.example.service;

import org.example.model.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final TransactionRepository repo;

    public DashboardService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Map<String, Double> getSummary(String userId) {

        List<Transaction> transactions =
                repo.findByUserUserIdAndDeletedFalse(userId);

        double income = 0;
        double expense = 0;

        for (Transaction t : transactions) {

            if ("EXPENSE".equalsIgnoreCase(t.getType())) {
                expense += t.getAmount();
            } else {
                income += t.getAmount();
            }
        }

        Map<String, Double> result = new HashMap<>();
        result.put("income", income);
        result.put("expense", expense);
        result.put("balance", income - expense);

        return result;
    }
}