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

    public SavingsGoal create(User user,
                              String title,
                              Double target,
                              LocalDate date) {

        SavingsGoal goal =
                new SavingsGoalBuilder()
                        .setUser(user)
                        .setTitle(title)
                        .setTargetAmount(target)
                        .setCurrentAmount(0.0)
                        .setTargetDate(date)
                        .setStatus("IN_PROGRESS")
                        .build();

        repo.save(goal);

        return goal;
    }

    public SavingsGoal addProgress(Long id, Double amount) {

        SavingsGoal goal = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Goal not found"));

        double current =
                goal.getCurrentAmount() == null
                        ? 0
                        : goal.getCurrentAmount();

        goal.setCurrentAmount(current + amount);

        if (goal.getCurrentAmount() >= goal.getTargetAmount()) {
            goal.setStatus("ACHIEVED");
        }

        repo.save(goal);

        return goal;
    }

    public List<SavingsGoal> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.delete(id);
    }
}