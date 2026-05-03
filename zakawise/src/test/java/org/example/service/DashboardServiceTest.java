package org.example.service;

import org.example.model.Transaction;
import org.example.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    TransactionRepository repo;

    @InjectMocks
    DashboardService service;

    @Test
    void getSummary() {

        Transaction t1 = new Transaction();
        t1.setType("INCOME");
        t1.setAmount(1000.0);

        Transaction t2 = new Transaction();
        t2.setType("EXPENSE");
        t2.setAmount(300.0);

        when(repo.findByUserUserIdAndDeletedFalse("1"))
                .thenReturn(List.of(t1, t2));

        Map<String, Double> result = service.getSummary("1");

        assertEquals(1000.0, result.get("income"));
        assertEquals(300.0, result.get("expense"));
        assertEquals(700.0, result.get("balance"));
    }
}