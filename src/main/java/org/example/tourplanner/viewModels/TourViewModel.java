package org.example.tourplanner.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.tourplanner.Model.Tour;

public class TourViewModel {
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
    private static TourViewModel viewModel;

    private TourViewModel() {
        tours.addAll(
                new Tour("Wienerwald", "wald", "a", "b", "bike", 20, "2 hours"),
                new Tour("Dopplerhütte", "qwe", "b", "c", "Hike", 10, "1 hours"),
                new Tour("Figlwarte", "asd", "c", "d", "bike", 30, "3 hours"),
                new Tour("Dorfrunde", "yxc", "d", "e", "Running", 12, "1 hours")
        );
    }

    public static TourViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new TourViewModel();
        }
        return viewModel;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void removeTour(Tour tour) {
        tours.remove(tour);
    }

    public Tour getTourByName(String name){
        for (Tour tour : tours) {
            if (tour.getName().equalsIgnoreCase(name)) {
                return tour;
            }
        }
        return null;
    }

    public void editTour(Tour oldTour, String name, String description, String from, String to, String transportType, double distance, String estimatedTime) {
        Tour updatedTour = getTourByName(oldTour.getName());
        updatedTour.setName(name);
        updatedTour.setDescription(description);
        updatedTour.setFrom(from);
        updatedTour.setTo(to);
        updatedTour.setTransportType(transportType);
        updatedTour.setDistance(distance);
        updatedTour.setEstimatedTime(estimatedTime);
    }
}
