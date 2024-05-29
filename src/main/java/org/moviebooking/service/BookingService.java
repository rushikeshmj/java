package org.moviebooking.service;

import org.moviebooking.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();

    void bookShow(Long showId);

}

