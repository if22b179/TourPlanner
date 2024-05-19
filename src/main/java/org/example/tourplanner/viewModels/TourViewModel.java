package org.example.tourplanner.viewModels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.tourplanner.Model.Tour;
import org.example.tourplanner.Model.TourLog;

public class TourViewModel {
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
    private static TourViewModel viewModel;

    private TourViewModel() {
        tours.addAll(
                new Tour(new SimpleStringProperty("Wienerwald"),
                         new SimpleStringProperty("wald"),
                         new SimpleStringProperty("a"),
                         new SimpleStringProperty("b"),
                         new SimpleStringProperty("bike"),
                         new SimpleDoubleProperty(20),
                         new SimpleStringProperty("2 hours"),
                         null),
                new Tour(new SimpleStringProperty("Dopplerhütte"),
                        new SimpleStringProperty("wald"),
                        new SimpleStringProperty("a"),
                        new SimpleStringProperty("b"),
                        new SimpleStringProperty("bike"),
                        new SimpleDoubleProperty(10),
                        new SimpleStringProperty("2 hours"),
                        null),
                new Tour(new SimpleStringProperty("Dorfrunde"),
                        new SimpleStringProperty("wald"),
                        new SimpleStringProperty("a"),
                        new SimpleStringProperty("b"),
                        new SimpleStringProperty("bike"),
                        new SimpleDoubleProperty(15),
                        new SimpleStringProperty("2 hours"),
                        null)
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
            if (tour.getName().toString().equalsIgnoreCase(name)) {
                return tour;
            }
        }
        return null;
    }

    public void editTour(Tour oldTour, String name, String description, String from, String to, String transportType, double distance, String estimatedTime) {
        Tour updatedTour = getTourByName(oldTour.getName().toString());
        updatedTour.setName(new SimpleStringProperty(name));
        updatedTour.setDescription(new SimpleStringProperty(description));
        updatedTour.setFrom(new SimpleStringProperty(from));
        updatedTour.setTo(new SimpleStringProperty(to));
        updatedTour.setTransportType(new SimpleStringProperty(transportType));
        updatedTour.setDistance(new SimpleDoubleProperty(distance));
        updatedTour.setEstimatedTime(new SimpleStringProperty(estimatedTime));
        updatedTour.setImage(null);
    }

    public void addTourLog(Tour tour, TourLog log) {
        tour.getTourLogs().add(log);
    }

    public void removeTourLog(Tour tour, TourLog log) {
        tour.getTourLogs().remove(log);
    }
}
