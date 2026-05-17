package org.example.api;

import org.example.model.Budget;
import org.example.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/budgets")
@Tag(name = "Budgets", description = "Budget management APIs")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create budget")
    public Budget create(@RequestBody Budget budget) {
        return service.create(
                budget.getUser(),
                budget.getName(),
                budget.getLimitAmount()
        );
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user budgets")
    public List<Budget> getUserBudgets(@PathVariable String userId) {
        return service.getUserBudgets();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update budget limit")
    public Budget updateLimit(@PathVariable Long id,
                              @RequestParam Double limit) {
        return service.updateLimit(id, limit);
    }
}