package org.example.creational_patterns;

import org.example.model.Budget;
import org.example.model.User;

public class BudgetFactory {

    public static Budget create(User user, String name, Double limit) {
        Budget b = new Budget();
        b.setUser(user);
        b.setName(name);
        b.setLimitAmount(limit);
        return b;
    }
}