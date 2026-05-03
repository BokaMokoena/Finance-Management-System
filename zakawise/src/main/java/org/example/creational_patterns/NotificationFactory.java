package org.example.creational_patterns;

import org.example.model.Notification;
import org.example.model.User;

import java.util.Date;
import java.util.UUID;

public class NotificationFactory {

    public static Notification create(User user, String message) {

        Notification n = new Notification();
        n.setNotificationId(UUID.randomUUID().toString());
        n.setUser(user);
        n.setMessage(message);
        n.setStatus("UNREAD");
        n.setDate(new Date());

        return n;
    }
}