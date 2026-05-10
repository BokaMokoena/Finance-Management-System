package org.example.InMemoryRepos;

import org.example.model.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<Long, Transaction> storage = new HashMap<>();

    @Override
    public void save(Transaction entity) {
        storage.put(entity.getId(), entity);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    @Override
    public List<Transaction> findByUserUserIdAndDeletedFalse(String userId) {

        List<Transaction> results = new ArrayList<>();

        for (Transaction t : storage.values()) {

            if (t.getUser() != null
                    && t.getUser().getUserId().equals(userId)
                    && !t.isDeleted()) {

                results.add(t);
            }
        }

        return results;
    }
}