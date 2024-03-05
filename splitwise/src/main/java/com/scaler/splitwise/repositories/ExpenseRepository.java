package com.scaler.splitwise.repositories;

import com.scaler.splitwise.modles.Expense;
import com.scaler.splitwise.modles.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
