package org.example.api;

import org.example.model.Budget;
import org.example.model.User;
import org.example.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping
    public Budget create(@RequestBody Budget budget) {

        User user = budget.getUser();

        return service.create(
                user,
                budget.getName(),
                budget.getLimitAmount()
        );
    }

    @GetMapping
    public List<Budget> getUserBudgets(
            @RequestBody User user) {

        return service.getUserBudgets();
    }

    @PutMapping("/{id}")
    public Budget updateLimit(
            @PathVariable Long id,
            @RequestParam Double limit) {

        return service.updateLimit(id, limit);
    }
}