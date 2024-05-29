package org.moviebooking.controller;

import org.moviebooking.metrics.HttpMetrics;
import org.moviebooking.metrics.ShowPerformanceMetrics;
import org.moviebooking.model.Show;
import org.moviebooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;
    private final ShowPerformanceMetrics showPerformanceMetrics;

    private final HttpMetrics httpMetrics; // Inject HttpMetrics


    @Autowired
    public ShowController(ShowService showService, ShowPerformanceMetrics showPerformanceMetrics, HttpMetrics httpMetrics) {
        this.showService = showService;
        this.showPerformanceMetrics = showPerformanceMetrics;
        this.httpMetrics = httpMetrics;
    }


    @GetMapping("/getAllShows")
    public List<Show> getAllShows() {
        // Start recording the execution time using summary metrics
        long startTime = System.currentTimeMillis();

        // Call the method to generate shows
        List<Show> shows = showService.getAllShows();

        // Calculate the elapsed time in milliseconds
        long elapsedTime = System.currentTimeMillis() - startTime;

        // Record the timing using ShowPerformanceMetrics
        showPerformanceMetrics.recordGenerateShowsTime(elapsedTime);


        httpMetrics.recordHttpStatusCode("/api/shows/getAllShows", "GET", "200");

        return shows;
    }

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody Show show) {
        showService.addShow(show);
        return ResponseEntity.status(HttpStatus.CREATED).body("Show added successfully");
    }

    @PostMapping("/{showId}/book")
    public ResponseEntity<String> bookShow(@PathVariable Long showId) {
        showService.bookShow(showId);
        return ResponseEntity.ok("Show booked successfully");
    }

    @GetMapping("/getShowsByLanguage")
      public List<Show> getShowsByLanguage(@RequestParam String language) {
            return showService.getShowsByLanguage(language);
        }


}
