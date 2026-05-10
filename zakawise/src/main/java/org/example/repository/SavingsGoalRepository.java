package org.example.repository;

import org.example.model.SavingsGoal;

import java.util.List;

public interface SavingsGoalRepository
        extends Repository<SavingsGoal, Long> {

    List<SavingsGoal> findByUserUserId(String userId);
}