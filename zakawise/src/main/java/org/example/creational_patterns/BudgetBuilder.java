package org.example.creational_patterns;

import org.example.model.Budget;
import org.example.model.User;

public class BudgetBuilder {

    private final Budget b = new Budget();

    public BudgetBuilder setUser(User user) {
        b.setUser(user);
        return this;
    }

    public BudgetBuilder setName(String name) {
        b.setName(name);
        return this;
    }

    public BudgetBuilder setLimit(Double limit) {
        b.setLimitAmount(limit);
        return this;
    }

    public Budget build() {
        return b;
    }
}