package org.example.service;

import org.example.model.Notification;
import org.example.model.User;
import org.example.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    NotificationRepository repo;

    @InjectMocks
    NotificationService service;

    @Test
    void send() {

        User user = new User();
        user.setUserId("1");

        Notification result = service.send(user, "Hello");

        assertNotNull(result);

        verify(repo).save(any(Notification.class));
    }

    @Test
    void getUserNotifications() {

        when(repo.findByUserUserId("1"))
                .thenReturn(List.of(new Notification()));

        List<Notification> result = service.getAllByUser("1");

        assertEquals(1, result.size());
    }

    @Test
    void markRead() {

        Notification n = new Notification();

        when(repo.findById("1")).thenReturn(Optional.of(n));

        Notification result = service.markRead("1");

        assertEquals("READ", result.getStatus());

        verify(repo).save(n);
    }
}