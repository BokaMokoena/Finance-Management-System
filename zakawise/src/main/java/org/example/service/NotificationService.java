package org.example.service;

import org.example.creational_patterns.NotificationBuilder;
import org.example.model.Notification;
import org.example.model.User;
import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public Notification send(User user, String message) {

        Notification n = new NotificationBuilder()
                .setUser(user)
                .setMessage(message)
                .setStatus("UNREAD")
                .build();

        return repo.save(n);
    }

    public List<Notification> getUserNotifications(String userId) {
        return repo.findByUserUserId(userId);
    }

    public Notification markRead(String id) {

        Notification n = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Notification not found")
        );

        n.setStatus("READ");
        return repo.save(n);
    }
}