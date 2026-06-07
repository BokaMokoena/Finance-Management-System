package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;
    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId("user123");
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");

        testTransaction = new Transaction();
        testTransaction.setId(1L);
        testTransaction.setUser(testUser);
        testTransaction.setDescription("Grocery shopping");
        testTransaction.setAmount(150.0);
        testTransaction.setType("EXPENSE");
    }

    @Test
    void create_shouldReturnCreatedTransaction_whenValidInput() throws Exception {
        when(transactionService.create(org.mockito.ArgumentMatchers.any(User.class), anyString(), anyDouble(), anyString()))
                .thenReturn(testTransaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTransaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("Grocery shopping")))
                .andExpect(jsonPath("$.amount", is(150.0)))
                .andExpect(jsonPath("$.type", is("EXPENSE")));

        verify(transactionService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), eq("Grocery shopping"), eq(150.0), eq("EXPENSE"));
    }

    @Test
    void create_shouldHandleIncomeTransaction() throws Exception {
        Transaction incomeTransaction = new Transaction();
        incomeTransaction.setId(2L);
        incomeTransaction.setUser(testUser);
        incomeTransaction.setDescription("Salary");
        incomeTransaction.setAmount(5000.0);
        incomeTransaction.setType("INCOME");

        when(transactionService.create(org.mockito.ArgumentMatchers.any(User.class), anyString(), anyDouble(), anyString()))
                .thenReturn(incomeTransaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomeTransaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("INCOME")))
                .andExpect(jsonPath("$.amount", is(5000.0)));

        verify(transactionService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), eq("Salary"), eq(5000.0), eq("INCOME"));
    }

    @Test
    void getUserTransactions_shouldReturnUserTransactions_whenUserExists() throws Exception {
        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setUser(testUser);
        transaction2.setDescription("Coffee");
        transaction2.setAmount(5.0);
        transaction2.setType("EXPENSE");

        List<Transaction> transactions = Arrays.asList(testTransaction, transaction2);

        when(transactionService.getUserTransactions("user123")).thenReturn(transactions);

        mockMvc.perform(get("/api/transactions/user/user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description", is("Grocery shopping")))
                .andExpect(jsonPath("$[1].description", is("Coffee")));

        verify(transactionService, times(1)).getUserTransactions("user123");
    }

    @Test
    void getUserTransactions_shouldReturnEmptyList_whenNoTransactionsFound() throws Exception {
        when(transactionService.getUserTransactions("user999")).thenReturn(List.of());

        mockMvc.perform(get("/api/transactions/user/user999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(transactionService, times(1)).getUserTransactions("user999");
    }

    @Test
    void updateAmount_shouldReturnUpdatedTransaction_whenValidAmount() throws Exception {
        testTransaction.setAmount(200.0);

        when(transactionService.updateAmount(1L, 200.0)).thenReturn(testTransaction);

        mockMvc.perform(put("/api/transactions/1/amount")
                        .param("amount", "200.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.amount", is(200.0)));

        verify(transactionService, times(1)).updateAmount(1L, 200.0);
    }

    @Test
    void updateAmount_shouldThrowException_whenAmountIsNull() throws Exception {
        mockMvc.perform(put("/api/transactions/1/amount")
                        .param("amount", ""))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateAmount_shouldThrowException_whenAmountIsZero() throws Exception {
        when(transactionService.updateAmount(1L, 0.0))
                .thenThrow(new IllegalArgumentException("Amount must be greater than 0"));

        mockMvc.perform(put("/api/transactions/1/amount")
                        .param("amount", "0.0"))
                .andExpect(status().isBadRequest());

        verify(transactionService, times(1)).updateAmount(1L, 0.0);
    }

    @Test
    void updateAmount_shouldThrowException_whenAmountIsNegative() throws Exception {
        when(transactionService.updateAmount(1L, -50.0))
                .thenThrow(new IllegalArgumentException("Amount must be greater than 0"));

        mockMvc.perform(put("/api/transactions/1/amount")
                        .param("amount", "-50.0"))
                .andExpect(status().isBadRequest());

        verify(transactionService, times(1)).updateAmount(1L, -50.0);
    }

    @Test
    void updateAmount_shouldThrowException_whenTransactionNotFound() throws Exception {
        when(transactionService.updateAmount(999L, 100.0))
                .thenThrow(new RuntimeException("Transaction not found with id: 999"));

        mockMvc.perform(put("/api/transactions/999/amount")
                        .param("amount", "100.0"))
                .andExpect(status().isNotFound());

        verify(transactionService, times(1)).updateAmount(999L, 100.0);
    }

    @Test
    void delete_shouldMarkTransactionAsDeleted_whenTransactionExists() throws Exception {
        doNothing().when(transactionService).delete(1L);

        mockMvc.perform(delete("/api/transactions/1"))
                .andExpect(status().isOk());

        verify(transactionService, times(1)).delete(1L);
    }

    @Test
    void delete_shouldThrowException_whenTransactionNotFound() throws Exception {
        doThrow(new RuntimeException("Transaction not found with id: 999"))
                .when(transactionService).delete(999L);

        mockMvc.perform(delete("/api/transactions/999"))
                .andExpect(status().isNotFound());

        verify(transactionService, times(1)).delete(999L);
    }

    @Test
    void create_shouldHandleNullUser() throws Exception {
        Transaction nullUserTransaction = new Transaction();
        nullUserTransaction.setDescription("Test");
        nullUserTransaction.setAmount(100.0);
        nullUserTransaction.setType("EXPENSE");
        nullUserTransaction.setUser(null);

        when(transactionService.create(isNull(), anyString(), anyDouble(), anyString()))
                .thenThrow(new IllegalArgumentException("User cannot be null"));

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nullUserTransaction)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_shouldHandleEmptyDescription() throws Exception {
        testTransaction.setDescription("");

        when(transactionService.create(org.mockito.ArgumentMatchers.any(User.class), eq(""), anyDouble(), anyString()))
                .thenReturn(testTransaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTransaction)))
                .andExpect(status().isOk());

        verify(transactionService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), eq(""), anyDouble(), anyString());
    }

    @Test
    void getUserTransactions_shouldHandleInvalidUserId() throws Exception {
        when(transactionService.getUserTransactions(""))
                .thenThrow(new IllegalArgumentException("User ID cannot be empty"));

        mockMvc.perform(get("/api/transactions/user/"))
                .andExpect(status().isNotFound());
    }
}
