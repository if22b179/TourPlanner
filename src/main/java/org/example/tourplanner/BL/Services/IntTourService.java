package org.example.tourplanner.BL.Services;

import org.example.tourplanner.BL.Model.Tour;

import java.util.List;

public interface IntTourService {

    List<Tour> getAllTours();
    void addTour(Tour tour);
    void deleteTour(Tour tour);

    void updateTour(Tour tour);
}