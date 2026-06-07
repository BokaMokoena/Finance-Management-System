package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Budget;
import org.example.model.User;
import org.example.service.BudgetService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BudgetController.class)
class BudgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BudgetService budgetService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;
    private Budget testBudget;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId("user123");
        testUser.setName("Test User");

        testBudget = new Budget();
        testBudget.setId(1L);
        testBudget.setUser(testUser);
        testBudget.setName("Food");
        testBudget.setLimitAmount(1000.0);
        testBudget.setCurrentSpend(250.0);
    }

    @Test
    void create_shouldReturnCreatedBudget_whenValidInput() throws Exception {
        when(budgetService.create(org.mockito.ArgumentMatchers.any(User.class), anyString(), anyDouble()))
                .thenReturn(testBudget);

        mockMvc.perform(post("/api/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBudget)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Food")))
                .andExpect(jsonPath("$.limitAmount", is(1000.0)))
                .andExpect(jsonPath("$.currentSpend", is(250.0)));

        verify(budgetService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), eq("Food"), eq(1000.0));
    }

    @Test
    void create_shouldHandleMultipleBudgetCategories() throws Exception {
        Budget transportBudget = new Budget();
        transportBudget.setId(2L);
        transportBudget.setUser(testUser);
        transportBudget.setName("Transport");
        transportBudget.setLimitAmount(500.0);
        transportBudget.setCurrentSpend(0.0);

        when(budgetService.create(org.mockito.ArgumentMatchers.any(User.class), eq("Transport"), eq(500.0)))
                .thenReturn(transportBudget);

        mockMvc.perform(post("/api/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transportBudget)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Transport")))
                .andExpect(jsonPath("$.limitAmount", is(500.0)));

        verify(budgetService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), eq("Transport"), eq(500.0));
    }

    @Test
    void create_shouldHandleZeroLimitAmount() throws Exception {
        testBudget.setLimitAmount(0.0);

        when(budgetService.create(org.mockito.ArgumentMatchers.any(User.class), anyString(), eq(0.0)))
                .thenReturn(testBudget);

        mockMvc.perform(post("/api/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBudget)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limitAmount", is(0.0)));

        verify(budgetService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), anyString(), eq(0.0));
    }

    @Test
    void getUserBudgets_shouldReturnListOfBudgets_whenBudgetsExist() throws Exception {
        Budget budget2 = new Budget();
        budget2.setId(2L);
        budget2.setUser(testUser);
        budget2.setName("Entertainment");
        budget2.setLimitAmount(300.0);
        budget2.setCurrentSpend(100.0);

        List<Budget> budgets = Arrays.asList(testBudget, budget2);

        when(budgetService.getUserBudgets()).thenReturn(budgets);

        mockMvc.perform(get("/api/budgets/user/user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Food")))
                .andExpect(jsonPath("$[0].limitAmount", is(1000.0)))
                .andExpect(jsonPath("$[1].name", is("Entertainment")))
                .andExpect(jsonPath("$[1].limitAmount", is(300.0)));

        verify(budgetService, times(1)).getUserBudgets();
    }

    @Test
    void getUserBudgets_shouldReturnEmptyList_whenNoBudgetsFound() throws Exception {
        when(budgetService.getUserBudgets()).thenReturn(List.of());

        mockMvc.perform(get("/api/budgets/user/user999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(budgetService, times(1)).getUserBudgets();
    }

    @Test
    void updateLimit_shouldReturnUpdatedBudget_whenValidLimit() throws Exception {
        testBudget.setLimitAmount(1500.0);

        when(budgetService.updateLimit(1L, 1500.0)).thenReturn(testBudget);

        mockMvc.perform(put("/api/budgets/1")
                        .param("limit", "1500.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.limitAmount", is(1500.0)));

        verify(budgetService, times(1)).updateLimit(1L, 1500.0);
    }

    @Test
    void updateLimit_shouldHandleIncreaseInLimit() throws Exception {
        testBudget.setLimitAmount(2000.0);

        when(budgetService.updateLimit(1L, 2000.0)).thenReturn(testBudget);

        mockMvc.perform(put("/api/budgets/1")
                        .param("limit", "2000.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limitAmount", is(2000.0)));

        verify(budgetService, times(1)).updateLimit(1L, 2000.0);
    }

    @Test
    void updateLimit_shouldHandleDecreaseInLimit() throws Exception {
        testBudget.setLimitAmount(500.0);

        when(budgetService.updateLimit(1L, 500.0)).thenReturn(testBudget);

        mockMvc.perform(put("/api/budgets/1")
                        .param("limit", "500.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limitAmount", is(500.0)));

        verify(budgetService, times(1)).updateLimit(1L, 500.0);
    }

    @Test
    void updateLimit_shouldThrowException_whenBudgetNotFound() throws Exception {
        when(budgetService.updateLimit(999L, 1000.0))
                .thenThrow(new RuntimeException("Budget not found with id: 999"));

        mockMvc.perform(put("/api/budgets/999")
                        .param("limit", "1000.0"))
                .andExpect(status().isNotFound());

        verify(budgetService, times(1)).updateLimit(999L, 1000.0);
    }

    @Test
    void updateLimit_shouldHandleNegativeLimit() throws Exception {
        when(budgetService.updateLimit(1L, -100.0))
                .thenThrow(new IllegalArgumentException("Limit cannot be negative"));

        mockMvc.perform(put("/api/budgets/1")
                        .param("limit", "-100.0"))
                .andExpect(status().isBadRequest());

        verify(budgetService, times(1)).updateLimit(1L, -100.0);
    }

    @Test
    void updateLimit_shouldHandleZeroLimit() throws Exception {
        testBudget.setLimitAmount(0.0);

        when(budgetService.updateLimit(1L, 0.0)).thenReturn(testBudget);

        mockMvc.perform(put("/api/budgets/1")
                        .param("limit", "0.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limitAmount", is(0.0)));

        verify(budgetService, times(1)).updateLimit(1L, 0.0);
    }

    @Test
    void create_shouldHandleNullUser() throws Exception {
        Budget nullUserBudget = new Budget();
        nullUserBudget.setName("Test");
        nullUserBudget.setLimitAmount(100.0);
        nullUserBudget.setUser(null);

        when(budgetService.create(org.mockito.ArgumentMatchers.isNull(), anyString(), anyDouble()))
                .thenThrow(new IllegalArgumentException("User cannot be null"));

        mockMvc.perform(post("/api/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nullUserBudget)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_shouldHandleEmptyBudgetName() throws Exception {
        testBudget.setName("");

        when(budgetService.create(org.mockito.ArgumentMatchers.any(User.class), eq(""), anyDouble()))
                .thenReturn(testBudget);

        mockMvc.perform(post("/api/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBudget)))
                .andExpect(status().isOk());

        verify(budgetService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), eq(""), anyDouble());
    }

    @Test
    void create_shouldHandleLargeLimitAmount() throws Exception {
        testBudget.setLimitAmount(1000000.0);

        when(budgetService.create(org.mockito.ArgumentMatchers.any(User.class), anyString(), eq(1000000.0)))
                .thenReturn(testBudget);

        mockMvc.perform(post("/api/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBudget)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limitAmount", is(1000000.0)));

        verify(budgetService, times(1))
                .create(org.mockito.ArgumentMatchers.any(User.class), anyString(), eq(1000000.0));
    }

    @Test
    void updateLimit_shouldHandleMissingParameter() throws Exception {
        mockMvc.perform(put("/api/budgets/1"))
                .andExpect(status().isInternalServerError());

        verify(budgetService, never()).updateLimit(anyLong(), anyDouble());
    }
}
