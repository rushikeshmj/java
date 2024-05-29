package org.moviebooking.repository;

import org.moviebooking.model.Booking;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

