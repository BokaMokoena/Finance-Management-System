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

    public Budget create(User user, String name, Double limit) {

        Budget b = new BudgetBuilder()
                .setUser(user)
                .setName(name)
                .setLimit(limit)
                .build();

        return repo.save(b);
    }

    public List<Budget> getUserBudgets(User user) {
        return repo.findByUser(user);
    }

    public Budget updateLimit(Long id, Double limit) {

        Budget b = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Budget not found")
        );

        b.setLimitAmount(limit);
        return repo.save(b);
    }

    public void applyExpense(Transaction t) {

        repo.findByNameAndUser(t.getDescription(), t.getUser())
                .ifPresent(budget -> {

                    double newSpend = (budget.getLimitAmount() != null ? budget.getLimitAmount() : 0)
                            + t.getAmount();

                    budget.setLimitAmount(newSpend);

                    if (newSpend >= budget.getLimitAmount()) {
                        notificationService.send(
                                budget.getUser(),
                                "Budget limit exceeded!"
                        );
                    }

                    repo.save(budget);
                });
    }
}