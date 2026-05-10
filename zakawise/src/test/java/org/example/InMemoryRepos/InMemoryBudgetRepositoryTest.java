package org.example.InMemoryRepos;

import org.example.model.Budget;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryBudgetRepositoryTest {

    @Test
    void save() {

        InMemoryBudgetRepository repo = new InMemoryBudgetRepository();

        Budget b = new Budget();
        b.setId(1L);

        repo.save(b);

        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    void findById() {

        InMemoryBudgetRepository repo = new InMemoryBudgetRepository();

        Budget b = new Budget();
        b.setId(1L);

        repo.save(b);

        Optional<Budget> result = repo.findById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void findAll() {

        InMemoryBudgetRepository repo = new InMemoryBudgetRepository();

        Budget b1 = new Budget();
        b1.setId(1L);

        Budget b2 = new Budget();
        b2.setId(2L);

        repo.save(b1);
        repo.save(b2);

        assertEquals(2, repo.findAll().size());
    }

    @Test
    void delete() {

        InMemoryBudgetRepository repo = new InMemoryBudgetRepository();

        Budget b = new Budget();
        b.setId(1L);

        repo.save(b);
        repo.delete(1L);

        assertTrue(repo.findById(1L).isEmpty());
    }

    @Test
    void findByUser() {

        InMemoryBudgetRepository repo = new InMemoryBudgetRepository();

        User user = new User();
        user.setUserId("1");

        Budget b = new Budget();
        b.setId(1L);
        b.setUser(user);

        repo.save(b);

        List<Budget> result = repo.findByUser(user);

        assertEquals(1, result.size());
    }

    @Test
    void findByNameAndUser() {

        InMemoryBudgetRepository repo = new InMemoryBudgetRepository();

        User user = new User();
        user.setUserId("1");

        Budget b = new Budget();
        b.setId(1L);
        b.setName("Food");
        b.setUser(user);

        repo.save(b);

        Optional<Budget> result =
                repo.findByNameAndUser("Food", user);

        assertTrue(result.isPresent());
    }
}