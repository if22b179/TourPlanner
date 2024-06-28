package org.example.tourplanner.BL.Services;

import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.DAL.repository.TourDAO;

import java.util.List;

public class TourService implements IntTourService {

    private final TourDAO tourDAO;

    public TourService() {
        this.tourDAO = new TourDAO();
    }


    @Override
    public List<Tour> getAllTours() {
        return tourDAO.findAll();
    }

    @Override
    public void addTour(Tour tour) {
        tourDAO.save(tour);
    }

    @Override
    public void deleteTour(Tour tour) {
        tourDAO.delete(tour);
    }

    @Override
    public void updateTour(Tour tour) {
        tourDAO.update(tour);
    }
}

