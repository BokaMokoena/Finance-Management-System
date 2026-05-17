package org.example.api;

import org.example.model.SavingsGoal;
import org.example.model.User;
import org.example.service.SavingsGoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class SavingsGoalController {

    private final SavingsGoalService service;

    public SavingsGoalController(SavingsGoalService service) {
        this.service = service;
    }

    @PostMapping
    public SavingsGoal create(@RequestBody SavingsGoal goal) {

        User user = goal.getUser();

        return service.create(
                user,
                goal.getTitle(),
                goal.getTargetAmount(),
                goal.getTargetDate()
        );
    }

    @PutMapping("/{id}/progress")
    public SavingsGoal addProgress(
            @PathVariable Long id,
            @RequestParam Double amount) {

        return service.addProgress(id, amount);
    }

    @GetMapping("/{userId}")
    public List<SavingsGoal> getUserGoals(
            @PathVariable String userId) {

        return service.getAll();
    }
}