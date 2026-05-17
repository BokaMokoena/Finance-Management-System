package org.example.api;

import org.example.model.Transaction;
import org.example.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Transaction management APIs")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create transaction")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transaction created"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    public Transaction create(@RequestBody Transaction transaction) {
        return service.create(
                transaction.getUser(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType()
        );
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get transactions by user")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        return service.getUserTransactions(userId);
    }

    @PutMapping("/{id}/amount")
    @Operation(summary = "Update transaction amount")
    public Transaction updateAmount(@PathVariable Long id,
                                    @RequestParam Double amount) {
        return service.updateAmount(id, amount);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}