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

    public User create(String id, String name, String email, Double salary, String occupation) {

        User user = new UserBuilder()
                .setUserId(id)
                .setName(name)
                .setEmail(email)
                .setSalary(salary)
                .setOccupation(occupation)
                .build();

        return repo.save(user);
    }

    public User get(String id) {
        return repo.findById(id).orElseThrow();
    }

    public User update(String id, User updated) {
        User u = repo.findById(id).orElseThrow();

        u.setName(updated.getName());
        u.setEmail(updated.getEmail());
        u.setSalary(updated.getSalary());
        u.setOccupation(updated.getOccupation());

        return repo.save(u);
    }
}