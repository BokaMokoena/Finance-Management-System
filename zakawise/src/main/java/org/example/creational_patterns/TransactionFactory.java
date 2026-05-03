package org.example.creational_patterns;

import org.example.model.Transaction;
import org.example.model.User;

public class TransactionFactory {

    public static Transaction create(User user, String description, Double amount, String type) {
        Transaction t = new Transaction();
        t.setUser(user);
        t.setDescription(description);
        t.setAmount(amount);
        t.setType(type);
        t.setDeleted(false);
        return t;
    }
}