package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.modles.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    Booking save(Booking booking);
}
