package org.example.tourplanner.viewModels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.UI.viewmodel.TourViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TourViewModelTest {
    private TourViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = TourViewModel.getViewModel();
    }

    @Test
    public void testRemoveTour() {
        // Arrange
        Tour tourToRemove = new Tour(
                "TestTour",
                "Description",
                "A",
                "B",
                "Bike",
                10.0,
                "1 hour",
                null
        );
        viewModel.addTour(tourToRemove);

        // Act
        viewModel.removeTour(tourToRemove);

        // Assert
        assertFalse(viewModel.getTours().contains(tourToRemove), "Tour should be removed from the list");
        // Constructor of viewModel adds 3 as default
        assertEquals(4, viewModel.getTours().size(), "Tour list should be empty after removal");
    }

    @Test
    public void testAddTour() {
        Tour newTour = new Tour("New Tour",
                                "description",
                                "from",
                                "to",
                                "bike",
                                15.0,
                                "1.5 hours",
                                null);
        viewModel.addTour(newTour);

        Tour addedTour = viewModel.getTourByName("New Tour");
        assertEquals(newTour, addedTour);
    }
}
