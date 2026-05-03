package org.example.creational_patterns;

import org.example.model.SavingsGoal;
import org.example.model.User;

import java.time.LocalDate;

public class SavingsGoalFactory {

    public static SavingsGoal create(User user, String title, Double target, LocalDate date) {
        SavingsGoal g = new SavingsGoal();
        g.setUser(user);
        g.setTitle(title);
        g.setTargetAmount(target);
        g.setCurrentAmount(0.0);
        g.setTargetDate(date);
        g.setStatus("IN_PROGRESS");
        return g;
    }
}