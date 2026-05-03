package org.example.service;

import org.example.model.Notification;
import org.example.model.User;
import org.example.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

        Notification n = new Notification();

        when(repo.save(any())).thenReturn(n);

        Notification result = service.send(user, "Hello");

        assertNotNull(result);
        verify(repo).save(any());
    }

    @Test
    void getUserNotifications() {
        when(repo.findByUserUserId("1"))
                .thenReturn(List.of(new Notification()));

        List<Notification> result = service.getUserNotifications("1");

        assertFalse(result.isEmpty());
    }

    @Test
    void markRead() {
        Notification n = new Notification();

        when(repo.findById("1")).thenReturn(Optional.of(n));
        when(repo.save(any())).thenReturn(n);

        Notification result = service.markRead("1");

        assertEquals("READ", result.getStatus());
    }
}