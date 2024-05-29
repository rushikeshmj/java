package org.moviebooking.service;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.moviebooking.model.Booking;
import org.moviebooking.model.Show;
import org.moviebooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.moviebooking.repository.BookingRepository;
import org.moviebooking.repository.ShowRepository;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;

    private final MeterRegistry meterRegistry; // Add MeterRegistry for metrics

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ShowRepository showRepository, MeterRegistry meterRegistry) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.meterRegistry = meterRegistry;

        // Register a gauge to monitor the total number of shows
        Gauge.builder("bookings.total", this::countBookings)
                .description("Total number of bookings")
                .register(meterRegistry);
    }

    // Method to count the total number of shows
    private long countBookings() {
        return bookingRepository.count();

    }


    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    @Override
    public void bookShow(Long showId) {
        // Create a booking and save it to the database
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found with id: " + showId));
        Booking booking = new Booking();
        booking.setShow(show);
        bookingRepository.save(booking);
    }


    }
