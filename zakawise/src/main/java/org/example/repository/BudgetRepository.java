package org.example.repository;

import org.example.model.Budget;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUser(User user);

    Optional<Budget> findByNameAndUser(String name, User user);
}
