package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.modles.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {

    Optional<Show> findById(Long aLong);
}
