package org.example.creational_patterns;

import org.example.model.SavingsGoal;
import org.example.model.User;

import java.time.LocalDate;

public class SavingsGoalBuilder {

    private final SavingsGoal g = new SavingsGoal();

    public SavingsGoalBuilder setUser(User user) {
        g.setUser(user);
        return this;
    }

    public SavingsGoalBuilder setTitle(String title) {
        g.setTitle(title);
        return this;
    }

    public SavingsGoalBuilder setTargetAmount(Double target) {
        g.setTargetAmount(target);
        return this;
    }

    public SavingsGoalBuilder setTargetDate(LocalDate date) {
        g.setTargetDate(date);
        return this;
    }

    public SavingsGoal build() {
        g.setCurrentAmount(0.0);
        g.setStatus("IN_PROGRESS");
        return g;
    }
}