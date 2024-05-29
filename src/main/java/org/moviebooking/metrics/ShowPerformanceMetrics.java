package org.moviebooking.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ShowPerformanceMetrics {

    private final Timer generateShowsTimer;

    @Autowired
    public ShowPerformanceMetrics(MeterRegistry meterRegistry) {
        this.generateShowsTimer = Timer.builder("shows.generate.timer")
                .description("Timer for generateShowsForWeek method")
                .tags("action", "generateShows")
                .register(meterRegistry);
    }

    public void recordGenerateShowsTime(long elapsedTimeInMillis) {
        generateShowsTimer.record(Duration.ofMillis(elapsedTimeInMillis));
    }
}
