package org.example.api;

import org.example.model.Transaction;
import org.example.model.User;
import org.example.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {

        User user = transaction.getUser();

        return service.create(
                user,
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType()
        );
    }

    @GetMapping("/{userId}")
    public List<Transaction> getUserTransactions(
            @PathVariable String userId) {

        return service.getUserTransactions(userId);
    }

    @PutMapping("/{id}")
    public Transaction updateAmount(
            @PathVariable Long id,
            @RequestParam Double amount) {

        return service.updateAmount(id, amount);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}