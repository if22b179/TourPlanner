package org.example.tourplanner.UI.viewmodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.BL.Model.TourLog;

@Getter
public class TourViewModel {
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
    private static TourViewModel viewModel;

    private TourViewModel() {
        tours.addAll(
                new Tour("Wienerwald",
                         "wald",
                         "a",
                         "b",
                         "bike",
                         20.0,
                         "2 hours",
                         null),
                new Tour("Dopplerhütte",
                        "wald",
                        "a",
                        "b",
                        "bike",
                        10.0,
                        "2 hours",
                        null),
                new Tour("Dorfrunde",
                        "wald",
                        "a",
                        "b",
                        "bike",
                        15.0,
                        "2 hours",
                        null)
                );
    }

    public static TourViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new TourViewModel();
        }
        return viewModel;
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void removeTour(Tour tour) {
        tours.remove(tour);
    }

    public Tour getTourByName(String name){
        for (Tour tour : tours) {
            if (tour.getName().toString().equalsIgnoreCase(name)) {
                return tour;
            }
        }
        return null;
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
        updatedTour.setImage(null);
    }

    public void addTourLog(Tour tour, TourLog log) {
        tour.getTourLogs().add(log);
    }

    public void removeTourLog(Tour tour, TourLog log) {
        tour.getTourLogs().remove(log);
    }
}
