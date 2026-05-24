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

        User result = service.create("1", "John", "john@mail.com", 5000.0, "Dev");

        assertNotNull(result);
        assertEquals("1", result.getUserId());

        verify(repo).save(any(User.class));
    }

    @Test
    void get() {

        User user = new User();
        user.setUserId("1");

        when(repo.findById("1")).thenReturn(Optional.of(user));

        User result = service.get("1");

        assertEquals("1", result.getUserId());

        verify(repo).findById("1");
    }

    @Test
    void update() {

        User existing = new User();
        existing.setUserId("1");

        when(repo.findById("1")).thenReturn(Optional.of(existing));

        User updated = new User();
        updated.setName("New");

        User result = service.update("1", updated);

        assertEquals("New", result.getName());

        verify(repo).save(existing);
    }

    @Test
    void delete() {

        doNothing().when(repo).delete("1");

        service.delete("1");

        verify(repo).delete("1");
    }
}