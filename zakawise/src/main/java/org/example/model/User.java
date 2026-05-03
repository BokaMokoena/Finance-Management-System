package org.example.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import org.example.model.Notification;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;

    private String name;
    private String email;
    private Double salary;
    private String occupation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Budget> budgets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SavingsGoal> goals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public User() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public List<SavingsGoal> getGoals() {
        return goals;
    }

    public void setGoals(List<SavingsGoal> goals) {
        this.goals = goals;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    // getters and setters omitted for brevity (keep yours)

    @Override
    public boolean equals(Object o) {
        return o instanceof User u && Objects.equals(userId, u.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}