package org.moviebooking.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Entity
@Table(name = "movie_show")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieName;
    private String language;
    private String genre;
    private double ticketPrice;
    private Date datetime;
    private int availableBookings;
    private int totalBookings;
    private double capacityPercentage;

    public Show() {
    }

    public Show(String movieName, String language, String genre, double ticketPrice, Date datetime, int availableBookings, int totalBookings, double capacityPercentage) {
        this.movieName = movieName;
        this.language = language;
        this.genre = genre;
        this.ticketPrice = ticketPrice;
        this.datetime = datetime;
        this.availableBookings = availableBookings;
        this.totalBookings = totalBookings;
        this.capacityPercentage = capacityPercentage;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getAvailableBookings() {
        return availableBookings;
    }

    public void setAvailableBookings(int availableBookings) {
        this.availableBookings = availableBookings;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public double getCapacityPercentage() {
        return capacityPercentage;
    }

    public void setCapacityPercentage(double capacityPercentage) {
        this.capacityPercentage = capacityPercentage;
    }

    public void bookTicket() {
        totalBookings++;
        availableBookings--; // Assuming each booking reduces available bookings by 1
    }
}
