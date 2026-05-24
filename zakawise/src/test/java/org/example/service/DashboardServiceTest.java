package org.example.service;

import org.example.model.Transaction;
import org.example.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

        Transaction income = new Transaction();
        income.setType("INCOME");
        income.setAmount(1000.0);

        Transaction expense = new Transaction();
        expense.setType("EXPENSE");
        expense.setAmount(300.0);

        when(repo.findByUserUserIdAndDeletedFalse("1"))
                .thenReturn(List.of(income, expense));

        Map<String, Double> result = service.getSummary("1");

        assertEquals(1000.0, result.get("income"));
        assertEquals(300.0, result.get("expense"));
        assertEquals(700.0, result.get("balance"));
    }
}