package org.example.tourplanner.UI.viewmodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import lombok.Getter;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.BL.Model.TourLog;
import org.example.tourplanner.BL.Services.TourLogService;
import org.example.tourplanner.BL.Services.TourService;

import java.util.List;

@Getter
public class TourViewModel {
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
    private static TourViewModel viewModel;
    private final TourService tourService = new TourService();
    private final TourLogService tourLogService = new TourLogService();

    private TourViewModel() {
        loadTours();
    }

    public static TourViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new TourViewModel();
        }
        return viewModel;
    }

    private void loadTours() {
        try {
            List<Tour> dbTours = tourService.getAllTours();
            tours.setAll(dbTours);
        } catch (Exception e) {
            System.err.println("Failed to load tours from the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addTour(Tour tour) {
        tours.add(tour);
        tourService.addTour(tour);
    }

    public void removeTour(Tour tour) {
        tours.remove(tour);
        tourService.deleteTour(tour);
    }

    public Tour getTourByName(String name){

        return tourService.getTourByName(name);
    }

    public void editTour(Tour oldTour, String name, String description, String from, String to, String transportType, double distance, String estimatedTime) {
        Tour updatedTour = getTourByName(oldTour.getName().toString());
        updatedTour.setName(name);
        updatedTour.setDescription(description);
        updatedTour.setFrom(from);
        updatedTour.setTo(to);
        updatedTour.setTransportType(transportType);
        updatedTour.setDistance(distance);
        updatedTour.setEstimatedTime(estimatedTime);
        updatedTour.setImage(oldTour.getImage());
        tourService.updateTour(updatedTour);
        loadTours();
    }

    public void addTourLog(Tour tour, TourLog log) {
        log.setTour_id(tour.getId());
        tourLogService.addTourLog(log);
    }

    public void removeTourLog(Tour tour, TourLog log) {
        log.setTour_id(tour.getId());
        tourLogService.deleteTourLog(log);
    }

    public void editTourLog(Tour tour, TourLog log) {
        log.setTour_id(tour.getId());
        tourLogService.updateTourLog(log);
    }

    public List<TourLog> getTourLogs(int id){
        return tourLogService.getTourLogs(id);
    }
}
