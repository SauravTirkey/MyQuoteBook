package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.modles.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findById(Long along);

    Optional<User> findByEmail(String email);


    User save(User user);
}
