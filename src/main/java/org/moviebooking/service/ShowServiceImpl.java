package org.moviebooking.service;


import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.moviebooking.model.Booking;
import org.moviebooking.model.Show;
import org.moviebooking.repository.BookingRepository;
import org.moviebooking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;

    private final MeterRegistry meterRegistry; // Add MeterRegistry for metrics


    @Autowired
    public ShowServiceImpl(ShowRepository showRepository, BookingRepository bookingRepository, MeterRegistry meterRegistry) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.meterRegistry = meterRegistry;


        // Register a gauge to monitor the total number of shows
        Gauge.builder("shows.total", this::countShows)
                .description("Total number of shows")
                .register(meterRegistry);
    }

    // Method to count the total number of shows
    private long countShows() {
        return showRepository.count();

    }

    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public void addShow(Show show) {
        showRepository.save(show);
    }
    @Override
    public void bookShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found with id: " + showId));
        show.bookTicket();
        showRepository.save(show);
        // Create a booking and save it to the database
        Booking booking = new Booking();
        booking.setShow(show);
        bookingRepository.save(booking);
    }

    @Override
    public List<Show> getShowsByLanguage(String language) {
        return showRepository.findByLanguage(language);
    }

}
