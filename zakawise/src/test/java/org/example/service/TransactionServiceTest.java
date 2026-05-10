package org.example.service;

import org.example.model.Transaction;
import org.example.model.User;
import org.example.repository.TransactionRepository;
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
class TransactionServiceTest {

    @Mock
    TransactionRepository repo;

    @InjectMocks
    TransactionService service;

    @Test
    void create() {

        User user = new User();
        user.setUserId("1");

        Transaction result = service.create(user, "Food", 100.0, "EXPENSE");

        assertNotNull(result);
        assertEquals(100.0, result.getAmount());

        verify(repo).save(any(Transaction.class));
    }

    @Test
    void get() {

        Transaction t = new Transaction();

        when(repo.findById(1L)).thenReturn(Optional.of(t));

        Transaction result = service.get(1L);

        assertNotNull(result);
        verify(repo).findById(1L);
    }

    @Test
    void getUserTransactions() {

        when(repo.findByUserUserIdAndDeletedFalse("1"))
                .thenReturn(List.of(new Transaction()));

        List<Transaction> result =
                service.getUserTransactions("1");

        assertEquals(1, result.size());
    }

    @Test
    void updateAmount() {

        Transaction t = new Transaction();
        t.setAmount(50.0);

        when(repo.findById(1L)).thenReturn(Optional.of(t));

        Transaction result = service.updateAmount(1L, 200.0);

        assertEquals(200.0, result.getAmount());
        verify(repo).save(t);
    }

    @Test
    void softDelete() {

        Transaction t = new Transaction();

        when(repo.findById(1L)).thenReturn(Optional.of(t));

        service.delete(1L);

        assertTrue(t.isDeleted());
        verify(repo).save(t);
    }
}