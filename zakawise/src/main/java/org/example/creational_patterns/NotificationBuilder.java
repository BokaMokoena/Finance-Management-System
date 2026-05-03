package org.example.creational_patterns;

import org.example.model.Notification;
import org.example.model.User;

import java.util.Date;
import java.util.UUID;

public class NotificationBuilder {

    private final Notification n = new Notification();

    public NotificationBuilder setUser(User user) {
        n.setUser(user);
        return this;
    }

    public NotificationBuilder setMessage(String message) {
        n.setMessage(message);
        return this;
    }

    public NotificationBuilder setStatus(String status) {
        n.setStatus(status);
        return this;
    }

    public Notification build() {
        n.setNotificationId(UUID.randomUUID().toString());
        n.setDate(new Date());
        return n;
    }
}