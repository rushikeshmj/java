package org.movibooking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.moviebooking.model.Show;
import java.util.Date;

public class ShowTest {
    private Show show;
    private Date date;

    @BeforeEach
    public void setup() {
        date = new Date();
        show = new Show("Tiger 3", "Hindi", "Action", 150.0, date, 50, 10, 33.33);
    }
    

    @Test
    public void testSetters() {
        show.setMovieName("Tiger 3");
        assertEquals("Tiger 3", show.getMovieName());

        show.setLanguage("Hindi");
        assertEquals("Hindi", show.getLanguage());

        show.setGenre("Action");
        assertEquals("Action", show.getGenre());

        show.setTicketPrice(200.0);
        assertEquals(200.0, show.getTicketPrice());

        Date newDate = new Date();
        show.setDatetime(newDate);
        assertEquals(newDate, show.getDatetime());

        show.setAvailableBookings(20);
        assertEquals(20, show.getAvailableBookings());

        show.setTotalBookings(15);
        assertEquals(15, show.getTotalBookings());

        show.setCapacityPercentage(50.0);
        assertEquals(50.0, show.getCapacityPercentage());
    }


}
