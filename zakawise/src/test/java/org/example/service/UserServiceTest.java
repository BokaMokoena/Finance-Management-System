package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository repo;

    @InjectMocks
    UserService service;

    @Test
    void create() {
        User user = new User();
        user.setUserId("1");

        when(repo.save(any(User.class))).thenReturn(user);

        User result = service.create("1", "John", "john@mail.com", 5000.0, "Dev");

        assertNotNull(result);
        verify(repo, times(1)).save(any(User.class));
    }

    @Test
    void get() {
        User user = new User();
        user.setUserId("1");

        when(repo.findById("1")).thenReturn(Optional.of(user));

        User result = service.get("1");

        assertEquals("1", result.getUserId());
    }

    @Test
    void update() {
        User existing = new User();
        existing.setUserId("1");

        when(repo.findById("1")).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenReturn(existing);

        User updated = new User();
        updated.setName("New Name");

        User result = service.update("1", updated);

        assertEquals("New Name", result.getName());
    }
}