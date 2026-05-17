package org.example.service;

import org.example.creational_patterns.BudgetBuilder;
import org.example.model.Budget;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository repo;
    private final NotificationService notificationService;

    public BudgetService(BudgetRepository repo,
                         NotificationService notificationService) {

        this.repo = repo;
        this.notificationService = notificationService;
    }

    public Budget create(User user,
                         String name,
                         Double limit) {

        Budget budget =
                new BudgetBuilder()
                        .setUser(user)
                        .setName(name)
                        .setLimit(limit)
                        .setCurrentSpend(0.0)
                        .build();

        repo.save(budget);

        return budget;
    }

    public List<Budget> getUserBudgets() {
        return repo.findAll();
    }

    public Budget updateLimit(Long id, Double limit) {

        Budget budget = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Budget not found"));

        budget.setLimitAmount(limit);

        repo.save(budget);

        return budget;
    }

    public void applyExpense(Transaction transaction) {

        repo.findByNameAndUser(
                        transaction.getDescription(),
                        transaction.getUser()
                )
                .ifPresent(budget -> {

                    double current =
                            budget.getCurrentSpend() == null
                                    ? 0
                                    : budget.getCurrentSpend();

                    double updatedSpend =
                            current + transaction.getAmount();

                    budget.setCurrentSpend(updatedSpend);

                    if (updatedSpend >= budget.getLimitAmount()) {

                        notificationService.send(
                                budget.getUser(),
                                "Budget limit exceeded!"
                        );
                    }

                    repo.save(budget);
                });
    }

    public void delete(Long id) {
        repo.delete(id);
    }
}