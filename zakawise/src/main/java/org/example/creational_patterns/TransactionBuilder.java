package org.example.creational_patterns;

import org.example.model.Transaction;
import org.example.model.User;

public class TransactionBuilder {

    private final Transaction t = new Transaction();

    public TransactionBuilder setUser(User user) {
        t.setUser(user);
        return this;
    }

    public TransactionBuilder setDescription(String desc) {
        t.setDescription(desc);
        return this;
    }

    public TransactionBuilder setAmount(Double amount) {
        t.setAmount(amount);
        return this;
    }

    public TransactionBuilder setType(String type) {
        t.setType(type);
        return this;
    }

    public Transaction build() {
        return t;
    }
}