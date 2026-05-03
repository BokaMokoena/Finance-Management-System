package org.example.creational_patterns;

import org.example.model.User;

public class UserBuilder {

    private final User user = new User();

    public UserBuilder setUserId(String id) {
        user.setUserId(id);
        return this;
    }

    public UserBuilder setName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder setSalary(Double salary) {
        user.setSalary(salary);
        return this;
    }

    public UserBuilder setOccupation(String occupation) {
        user.setOccupation(occupation);
        return this;
    }

    public User build() {
        return user;
    }
}