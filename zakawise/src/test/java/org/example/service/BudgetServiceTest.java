package org.example.service;

import org.example.model.Budget;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.repository.BudgetRepository;
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
class BudgetServiceTest {

    @Mock
    BudgetRepository repo;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    BudgetService service;

    @Test
    void create() {

        User user = new User();

        Budget result = service.create(user, "Food", 1000.0);

        assertNotNull(result);
        assertEquals("Food", result.getName());

        verify(repo).save(any(Budget.class));
    }

    @Test
    void getAll() {

        when(repo.findAll())
                .thenReturn(List.of(new Budget()));

        List<Budget> result = service.getAll();

        assertEquals(1, result.size());

        verify(repo).findAll();
    }

    @Test
    void updateLimit() {

        Budget b = new Budget();

        when(repo.findById(1L)).thenReturn(Optional.of(b));

        Budget result = service.updateLimit(1L, 2000.0);

        assertEquals(2000.0, result.getLimitAmount());

        verify(repo).save(b);
    }

    @Test
    void applyExpense_shouldTriggerNotification_whenLimitExceeded() {

        User user = new User();
        user.setUserId("1");

        Budget b = new Budget();
        b.setUser(user);
        b.setLimitAmount(500.0);
        b.setCurrentSpend(0.0);

        when(repo.findByNameAndUser(anyString(), any(User.class)))
                .thenReturn(Optional.of(b));

        Transaction t = new Transaction();
        t.setUser(user);
        t.setAmount(600.0);
        t.setDescription("Food");

        service.applyExpense(t);

        verify(notificationService, times(1))
                .send(any(User.class), anyString());

        verify(repo).save(b);
    }
}