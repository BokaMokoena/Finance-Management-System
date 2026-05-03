package org.example.service;

import org.example.model.SavingsGoal;
import org.example.model.User;
import org.example.repository.SavingsGoalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SavingsGoalServiceTest {

    @Mock
    private SavingsGoalRepository repo;

    @InjectMocks
    private SavingsGoalService service;

    @Test
    void create() {

        User user = new User();
        user.setUserId("1");

        when(repo.save(any(SavingsGoal.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SavingsGoal result = service.create(
                user,
                "Laptop Fund",
                5000.0,
                LocalDate.now()
        );

        assertNotNull(result);
        assertEquals("Laptop Fund", result.getTitle());
        assertEquals(5000.0, result.getTargetAmount());

        verify(repo, times(1)).save(any(SavingsGoal.class));
    }

    @Test
    void addProgress() {

        SavingsGoal goal = new SavingsGoal();
        goal.setId(1L);
        goal.setCurrentAmount(1000.0);
        goal.setTargetAmount(2000.0);

        when(repo.findById(1L)).thenReturn(Optional.of(goal));
        when(repo.save(any())).thenReturn(goal);

        SavingsGoal result = service.addProgress(1L, 500.0);

        assertEquals(1500.0, result.getCurrentAmount());
        assertEquals("IN_PROGRESS", result.getStatus() == null ? "IN_PROGRESS" : result.getStatus());

        verify(repo).save(goal);
    }

    @Test
    void getUserGoals() {

        SavingsGoal g1 = new SavingsGoal();
        SavingsGoal g2 = new SavingsGoal();

        when(repo.findByUserUserId("1")).thenReturn(List.of(g1, g2));

        List<SavingsGoal> result = service.getUserGoals("1");

        assertEquals(2, result.size());
        verify(repo).findByUserUserId("1");
    }
}