package org.moviebooking.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpMetrics {

    private final Counter httpStatusCodeCounter;

    @Autowired
    public HttpMetrics(MeterRegistry meterRegistry) {
        this.httpStatusCodeCounter = Counter.builder("http.status.code")
                .description("HTTP status codes counter")
                .register(meterRegistry);
    }

    public void recordHttpStatusCode(String endpoint, String method, String status) {
        httpStatusCodeCounter
                .increment(); // Just increment, as we are not adding tags here
    }
}
