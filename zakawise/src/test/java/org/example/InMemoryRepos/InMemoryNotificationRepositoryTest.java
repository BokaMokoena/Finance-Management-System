package org.example.InMemoryRepos;

import org.example.model.Notification;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryNotificationRepositoryTest {

    @Test
    void save() {

        InMemoryNotificationRepository repo =
                new InMemoryNotificationRepository();

        Notification n = new Notification();
        n.setNotificationId("n1");

        repo.save(n);

        assertTrue(repo.findById("n1").isPresent());
    }

    @Test
    void findById() {

        InMemoryNotificationRepository repo =
                new InMemoryNotificationRepository();

        Notification n = new Notification();
        n.setNotificationId("n1");

        repo.save(n);

        Optional<Notification> result = repo.findById("n1");

        assertTrue(result.isPresent());
    }

    @Test
    void findAll() {

        InMemoryNotificationRepository repo =
                new InMemoryNotificationRepository();

        Notification n1 = new Notification();
        n1.setNotificationId("1");

        Notification n2 = new Notification();
        n2.setNotificationId("2");

        repo.save(n1);
        repo.save(n2);

        assertEquals(2, repo.findAll().size());
    }

    @Test
    void delete() {

        InMemoryNotificationRepository repo =
                new InMemoryNotificationRepository();

        Notification n = new Notification();
        n.setNotificationId("1");

        repo.save(n);
        repo.delete("1");

        assertTrue(repo.findById("1").isEmpty());
    }

    @Test
    void findByUserUserId() {

        InMemoryNotificationRepository repo =
                new InMemoryNotificationRepository();

        User user = new User();
        user.setUserId("1");

        Notification n = new Notification();
        n.setNotificationId("1");
        n.setUser(user);

        repo.save(n);

        List<Notification> result =
                repo.findByUserUserId("1");

        assertEquals(1, result.size());
    }
}