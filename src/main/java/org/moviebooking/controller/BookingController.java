package org.moviebooking.controller;

import org.moviebooking.metrics.HttpMetrics;
import org.moviebooking.metrics.ShowPerformanceMetrics;
import org.moviebooking.model.Booking;
import org.moviebooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final HttpMetrics httpMetrics;

    private final ShowPerformanceMetrics showPerformanceMetrics;

    @Autowired
    public BookingController(BookingService bookingService, HttpMetrics httpMetrics, ShowPerformanceMetrics showPerformanceMetrics) {
        this.bookingService = bookingService;
        this.httpMetrics = httpMetrics;
        this.showPerformanceMetrics = showPerformanceMetrics;
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        long startTime = System.currentTimeMillis();

        List<Booking> bookings = bookingService.getAllBookings();

        long elapsedTime = System.currentTimeMillis() - startTime;

        showPerformanceMetrics.recordGenerateShowsTime(elapsedTime);


        httpMetrics.recordHttpStatusCode("/api/bookings/getAllBookings", "GET", "200");

        return bookings;
    }
}

