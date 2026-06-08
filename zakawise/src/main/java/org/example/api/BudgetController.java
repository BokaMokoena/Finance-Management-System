package org.example.api;

import org.example.model.Budget;
import org.example.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Budget created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid budget data")
    })
    public ResponseEntity<?> create(@RequestBody Budget budget) {
        try {
            Budget created = service.create(
                    budget.getUser(),
                    budget.getName(),
                    budget.getLimitAmount()
            );
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create budget: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user budgets")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Budgets retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<?> getUserBudgets(@PathVariable String userId) {
        try {
            List<Budget> budgets = service.getUserBudgets();
            return ResponseEntity.ok(budgets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not retrieve budgets for user: " + userId);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update budget limit")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Budget limit updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid limit value"),
            @ApiResponse(responseCode = "404", description = "Budget not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> updateLimit(@PathVariable Long id,
                                         @RequestParam Double limit) {
        try {
            if (limit == null || limit <= 0) {
                return ResponseEntity.badRequest().body("Limit must be greater than zero.");
            }
            Budget updated = service.updateLimit(id, limit);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Budget with ID " + id + " not found.");
            }
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update budget: " + e.getMessage());
        }
    }
}
