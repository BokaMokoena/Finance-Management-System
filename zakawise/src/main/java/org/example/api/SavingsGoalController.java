package org.example.api;

import org.example.model.SavingsGoal;
import org.example.service.SavingsGoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/goals")
@Tag(name = "Savings Goals", description = "Savings goal management APIs")
public class SavingsGoalController {

    private final SavingsGoalService service;

    public SavingsGoalController(SavingsGoalService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create savings goal")
    public SavingsGoal create(@RequestBody SavingsGoal goal) {
        return service.create(
                goal.getUser(),
                goal.getTitle(),
                goal.getTargetAmount(),
                goal.getTargetDate()
        );
    }

    @PutMapping("/{id}/progress")
    @Operation(summary = "Add progress to goal")
    public SavingsGoal addProgress(@PathVariable Long id,
                                   @RequestParam Double amount) {
        return service.addProgress(id, amount);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user savings goals")
    public List<SavingsGoal> getUserGoals(@PathVariable String userId) {
        return service.getAll();
    }
}