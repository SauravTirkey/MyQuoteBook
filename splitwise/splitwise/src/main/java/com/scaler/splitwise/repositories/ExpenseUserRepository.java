package com.scaler.splitwise.repositories;

import com.scaler.splitwise.modles.ExpenseUser;
import com.scaler.splitwise.modles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser,Long> {

    List<ExpenseUser> findAllByUser(User user);
}
