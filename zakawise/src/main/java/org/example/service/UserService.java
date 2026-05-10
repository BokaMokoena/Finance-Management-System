package org.example.service;

import org.example.creational_patterns.UserBuilder;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(String id,
                       String name,
                       String email,
                       Double salary,
                       String occupation) {

        User user = new UserBuilder()
                .setUserId(id)
                .setName(name)
                .setEmail(email)
                .setSalary(salary)
                .setOccupation(occupation)
                .build();

        repo.save(user);
        return user;
    }

    public User get(String id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public User update(String id, User updated) {

        User user = get(id);

        user.setName(updated.getName());
        user.setEmail(updated.getEmail());
        user.setSalary(updated.getSalary());
        user.setOccupation(updated.getOccupation());

        repo.save(user);

        return user;
    }

    public void delete(String id) {
        repo.delete(id);
    }
}