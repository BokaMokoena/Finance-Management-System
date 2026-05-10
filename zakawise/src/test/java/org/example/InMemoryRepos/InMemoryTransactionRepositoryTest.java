package org.example.InMemoryRepos;

import org.example.model.Transaction;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepositoryTest {

    @Test
    void save() {

        InMemoryTransactionRepository repo =
                new InMemoryTransactionRepository();

        Transaction t = new Transaction();
        t.setId(1L);

        repo.save(t);

        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    void findById() {

        InMemoryTransactionRepository repo =
                new InMemoryTransactionRepository();

        Transaction t = new Transaction();
        t.setId(1L);

        repo.save(t);

        Optional<Transaction> result = repo.findById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void findAll() {

        InMemoryTransactionRepository repo =
                new InMemoryTransactionRepository();

        Transaction t1 = new Transaction();
        t1.setId(1L);

        Transaction t2 = new Transaction();
        t2.setId(2L);

        repo.save(t1);
        repo.save(t2);

        assertEquals(2, repo.findAll().size());
    }

    @Test
    void delete() {

        InMemoryTransactionRepository repo =
                new InMemoryTransactionRepository();

        Transaction t = new Transaction();
        t.setId(1L);

        repo.save(t);
        repo.delete(1L);

        assertTrue(repo.findById(1L).isEmpty());
    }

    @Test
    void findByUserUserIdAndDeletedFalse() {

        InMemoryTransactionRepository repo =
                new InMemoryTransactionRepository();

        User user = new User();
        user.setUserId("1");

        Transaction t = new Transaction();
        t.setId(1L);
        t.setUser(user);
        t.setDeleted(false);

        repo.save(t);

        List<Transaction> result =
                repo.findByUserUserIdAndDeletedFalse("1");

        assertEquals(1, result.size());
    }
}