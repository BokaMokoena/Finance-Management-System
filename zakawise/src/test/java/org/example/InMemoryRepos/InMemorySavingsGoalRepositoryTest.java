package org.example.InMemoryRepos;

import org.example.model.SavingsGoal;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemorySavingsGoalRepositoryTest {

    @Test
    void save() {

        InMemorySavingsGoalRepository repo =
                new InMemorySavingsGoalRepository();

        SavingsGoal g = new SavingsGoal();
        g.setId(1L);

        repo.save(g);

        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    void findById() {

        InMemorySavingsGoalRepository repo =
                new InMemorySavingsGoalRepository();

        SavingsGoal g = new SavingsGoal();
        g.setId(1L);

        repo.save(g);

        Optional<SavingsGoal> result = repo.findById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void findAll() {

        InMemorySavingsGoalRepository repo =
                new InMemorySavingsGoalRepository();

        SavingsGoal g1 = new SavingsGoal();
        g1.setId(1L);

        SavingsGoal g2 = new SavingsGoal();
        g2.setId(2L);

        repo.save(g1);
        repo.save(g2);

        assertEquals(2, repo.findAll().size());
    }

    @Test
    void delete() {

        InMemorySavingsGoalRepository repo =
                new InMemorySavingsGoalRepository();

        SavingsGoal g = new SavingsGoal();
        g.setId(1L);

        repo.save(g);
        repo.delete(1L);

        assertTrue(repo.findById(1L).isEmpty());
    }

    @Test
    void findByUserUserId() {

        InMemorySavingsGoalRepository repo =
                new InMemorySavingsGoalRepository();

        User user = new User();
        user.setUserId("1");

        SavingsGoal g = new SavingsGoal();
        g.setId(1L);
        g.setUser(user);

        repo.save(g);

        List<SavingsGoal> result =
                repo.findByUserUserId("1");

        assertEquals(1, result.size());
    }
}