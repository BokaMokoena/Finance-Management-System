package org.example.service;

import org.example.creational_patterns.NotificationBuilder;
import org.example.model.Notification;
import org.example.model.User;
import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public Notification send(User user, String message) {

        Notification notification =
                new NotificationBuilder()
                        .setUser(user)
                        .setMessage(message)
                        .setStatus("UNREAD")
                        .setDate(new Date())
                        .build();

        repo.save(notification);

        return notification;
    }

    public Notification get(String id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found"));
    }

    public List<Notification> getAll() {
        return repo.findAll();
    }

    public Notification markRead(String id) {

        Notification notification = get(id);

        notification.setStatus("READ");

        repo.save(notification);

        return notification;
    }

    public void delete(String id) {
        repo.delete(id);
    }
}