package org.example.creational_patterns;

import org.example.model.User;

public class UserFactory {

    public static User create(String id, String name, String email, Double salary, String occupation) {
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        user.setEmail(email);
        user.setSalary(salary);
        user.setOccupation(occupation);
        return user;
    }
}