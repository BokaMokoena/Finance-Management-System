package org.example.service;

import org.example.creational_patterns.SavingsGoalBuilder;
import org.example.model.SavingsGoal;
import org.example.model.User;
import org.example.repository.SavingsGoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SavingsGoalService {

    private final SavingsGoalRepository repo;

    public SavingsGoalService(SavingsGoalRepository repo) {
        this.repo = repo;
    }

    public SavingsGoal create(User user, String title, Double target, LocalDate date) {

        SavingsGoal g = new SavingsGoalBuilder()
                .setUser(user)
                .setTitle(title)
                .setTargetAmount(target)
                .setTargetDate(date)
                .build();

        return repo.save(g);
    }

    public SavingsGoal addProgress(Long id, Double amount) {

        SavingsGoal g = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Goal not found")
        );

        g.setCurrentAmount(
                (g.getCurrentAmount() == null ? 0 : g.getCurrentAmount()) + amount
        );

        if (g.getCurrentAmount() >= g.getTargetAmount()) {
            g.setStatus("ACHIEVED");
        }

        return repo.save(g);
    }

    public List<SavingsGoal> getUserGoals(String userId) {
        return repo.findByUserUserId(userId);
    }
}