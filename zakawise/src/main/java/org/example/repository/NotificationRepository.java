package org.example.repository;

import org.example.model.Notification;

import java.util.List;

public interface NotificationRepository
        extends Repository<Notification, String> {

    List<Notification> findByUserUserId(String userId);
}