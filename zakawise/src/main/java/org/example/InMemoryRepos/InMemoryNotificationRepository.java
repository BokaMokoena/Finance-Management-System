package org.example.InMemoryRepos;

import org.example.model.Notification;
import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryNotificationRepository
        implements NotificationRepository {

    private final Map<String, Notification> storage =
            new HashMap<>();

    @Override
    public void save(Notification entity) {
        storage.put(entity.getNotificationId(), entity);
    }

    @Override
    public Optional<Notification> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Notification> findByUserUserId(String userId) {

        List<Notification> results = new ArrayList<>();

        for (Notification n : storage.values()) {

            if (n.getUser() != null
                    && n.getUser().getUserId().equals(userId)) {

                results.add(n);
            }
        }

        return results;
    }
}