package org.example.InMemoryRepos;

import org.example.model.Budget;
import org.example.model.User;
import org.example.repository.BudgetRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryBudgetRepository implements BudgetRepository {

    private final Map<Long, Budget> storage = new HashMap<>();

    @Override
    public void save(Budget entity) {
        storage.put(entity.getId(), entity);
    }

    @Override
    public Optional<Budget> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Budget> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    @Override
    public List<Budget> findByUser(User user) {

        List<Budget> results = new ArrayList<>();

        for (Budget b : storage.values()) {

            if (b.getUser() != null
                    && b.getUser().equals(user)) {

                results.add(b);
            }
        }

        return results;
    }

    @Override
    public Optional<Budget> findByNameAndUser(String name, User user) {

        for (Budget b : storage.values()) {

            if (b.getName().equals(name)
                    && b.getUser().equals(user)) {

                return Optional.of(b);
            }
        }

        return Optional.empty();
    }
}