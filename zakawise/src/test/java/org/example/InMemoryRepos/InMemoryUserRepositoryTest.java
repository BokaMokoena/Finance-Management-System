package org.example.InMemoryRepos;

import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    @Test
    void save() {

        InMemoryUserRepository repo = new InMemoryUserRepository();

        User user = new User();
        user.setUserId("1");

        repo.save(user);

        assertTrue(repo.findById("1").isPresent());
    }

    @Test
    void findById() {

        InMemoryUserRepository repo = new InMemoryUserRepository();

        User user = new User();
        user.setUserId("1");

        repo.save(user);

        Optional<User> result = repo.findById("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getUserId());
    }

    @Test
    void findAll() {

        InMemoryUserRepository repo = new InMemoryUserRepository();

        User u1 = new User();
        u1.setUserId("1");

        User u2 = new User();
        u2.setUserId("2");

        repo.save(u1);
        repo.save(u2);

        List<User> result = repo.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void delete() {

        InMemoryUserRepository repo = new InMemoryUserRepository();

        User user = new User();
        user.setUserId("1");

        repo.save(user);
        repo.delete("1");

        assertTrue(repo.findById("1").isEmpty());
    }
}