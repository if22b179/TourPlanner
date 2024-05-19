package org.example.tourplanner.viewModels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.tourplanner.Model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourViewModelTest {
    private TourViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = TourViewModel.getViewModel();
    }

    @Test
    public void testEditTour() {
        Tour oldTour = viewModel.getTourByName("Wienerwald");
        String newName = "New Name";
        String newDescription = "Updated description";
        String newFrom = "Updated from";
        String newTo = "Updated to";
        String newTransportType = "Updated transport";
        double newDistance = 25.0;
        String newEstimatedTime = "2.5 hours";

        viewModel.editTour(oldTour, newName, newDescription, newFrom, newTo, newTransportType, newDistance, newEstimatedTime);

        Tour updatedTour = viewModel.getTourByName(newName);
        assertEquals(newName, updatedTour.getName());
        assertEquals(newDescription, updatedTour.getDescription());
        assertEquals(newFrom, updatedTour.getFrom());
        assertEquals(newTo, updatedTour.getTo());
        assertEquals(newTransportType, updatedTour.getTransportType());
        assertEquals(newDistance, updatedTour.getDistance());
        assertEquals(newEstimatedTime, updatedTour.getEstimatedTime());
    }

    @Test
    public void testAddTour() {
        Tour newTour = new Tour(new SimpleStringProperty("New Tour"),
                                new SimpleStringProperty("description"),
                                new SimpleStringProperty("from"),
                                new SimpleStringProperty("to"),
                                new SimpleStringProperty("bike"),
                                new SimpleDoubleProperty(15),
                                new SimpleStringProperty("1.5 hours"),
                                null);
        viewModel.addTour(newTour);

        Tour addedTour = viewModel.getTourByName("New Tour");
        assertEquals(newTour, addedTour);
    }
}
