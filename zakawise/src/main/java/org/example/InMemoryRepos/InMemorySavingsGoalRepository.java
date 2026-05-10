package org.example.InMemoryRepos;

import org.example.model.SavingsGoal;
import org.example.repository.SavingsGoalRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemorySavingsGoalRepository
        implements SavingsGoalRepository {

    private final Map<Long, SavingsGoal> storage =
            new HashMap<>();

    @Override
    public void save(SavingsGoal entity) {
        storage.put(entity.getId(), entity);
    }

    @Override
    public Optional<SavingsGoal> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<SavingsGoal> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    @Override
    public List<SavingsGoal> findByUserUserId(String userId) {

        List<SavingsGoal> results = new ArrayList<>();

        for (SavingsGoal g : storage.values()) {

            if (g.getUser() != null
                    && g.getUser().getUserId().equals(userId)) {

                results.add(g);
            }
        }

        return results;
    }
}