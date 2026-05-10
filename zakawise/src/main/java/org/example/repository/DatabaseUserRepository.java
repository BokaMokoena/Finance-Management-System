package org.example.repository;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseUserRepository implements UserRepository {

    @Override
    public void save(User user) {
        // TODO: Replace with INSERT INTO users (...) VALUES (...)
        System.out.println("[DB] Saving user to database: " + user.getUserId());
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO: SELECT * FROM users WHERE id = ?
        System.out.println("[DB] Fetching user from database: " + id);
        return Optional.empty(); // stub result
    }

    @Override
    public List<User> findAll() {
        // TODO: SELECT * FROM users
        System.out.println("[DB] Fetching all users from database");
        return new ArrayList<>(); // stub
    }

    @Override
    public void delete(String id) {
        // TODO: DELETE FROM users WHERE id = ?
        System.out.println("[DB] Deleting user from database: " + id);
    }
}