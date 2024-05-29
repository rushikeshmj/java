package org.moviebooking.service;

import org.moviebooking.model.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    List<Show> getAllShows();

    void addShow(Show show);

    void bookShow(Long showId);

    List<Show> getShowsByLanguage(String language);
}
