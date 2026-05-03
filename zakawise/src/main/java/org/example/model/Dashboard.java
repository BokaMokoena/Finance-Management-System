package org.example.model;

import java.util.List;

public class Dashboard {

    private double totalIncome;
    private double totalExpense;
    private double balance;

    public void generateReport(List<Transaction> transactions) {

        totalIncome = 0;
        totalExpense = 0;

        for (Transaction t : transactions) {
            if (t.isExpense()) {
                totalExpense += t.getAmount();
            } else {
                totalIncome += t.getAmount();
            }
        }

        balance = totalIncome - totalExpense;
    }

    public double getBalance() {
        return balance;
    }
}