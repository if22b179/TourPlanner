package org.example.tourplanner.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.BL.Model.TourLog;
import org.example.tourplanner.UI.viewmodel.TourViewModel;
@Slf4j
public class TourLogController {
    @FXML
    private TextField dateTimeField;
    @FXML
    private TextArea commentField;
    @FXML
    private Spinner<Integer> difficultySpinner;
    @FXML
    private TextField totalDistanceField;
    @FXML
    private TextField totalTimeField;
    @FXML
    private Spinner<Integer> ratingSpinner;

    private TourLog tourLog;
    private Tour tour;
    private TourViewModel tourViewModel;

    public void initialize() {
        // Initialize difficulty spinner
        SpinnerValueFactory<Integer> difficultyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        difficultySpinner.setValueFactory(difficultyValueFactory);

        // Initialize rating spinner
        SpinnerValueFactory<Integer> ratingValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        ratingSpinner.setValueFactory(ratingValueFactory);
    }

    public void setTourLog(TourLog tourLog) {
        this.tourLog = tourLog;
        if (tourLog != null) {
            dateTimeField.setText(tourLog.getDateTime());
            commentField.setText(tourLog.getComment());
            difficultySpinner.getValueFactory().setValue(tourLog.getDifficulty());
            totalDistanceField.setText(String.valueOf(tourLog.getTotalDistance()));
            totalTimeField.setText(String.valueOf(tourLog.getTotalTime()));
            ratingSpinner.getValueFactory().setValue(tourLog.getRating());
        }
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setTourViewModel(TourViewModel tourViewModel) {
        this.tourViewModel = tourViewModel;
    }


    @FXML
    private void handleSave() {
        if (!validateInputs()) {
            return;
        }

        if (tourLog == null) {
            // Creating a new TourLog
            TourLog newLog = new TourLog(
                    dateTimeField.getText(),
                    commentField.getText(),
                    difficultySpinner.getValue(),
                    Double.parseDouble(totalDistanceField.getText()),
                    Double.parseDouble(totalTimeField.getText()),
                    ratingSpinner.getValue()
            );
            tourViewModel.addTourLog(tour, newLog);
        } else {
            // Editing an existing TourLog
            tourLog.setDateTime(dateTimeField.getText());
            tourLog.setComment(commentField.getText());
            tourLog.setDifficulty(difficultySpinner.getValue());
            tourLog.setTotalDistance(Double.parseDouble(totalDistanceField.getText()));
            tourLog.setTotalTime(Double.parseDouble(totalTimeField.getText()));
            tourLog.setRating(ratingSpinner.getValue());
            tourViewModel.editTourLog(tour,tourLog);
        }
        // Close the window after saving
        ((Stage) dateTimeField.getScene().getWindow()).close();
    }

    private boolean validateInputs() {
        // Validate date/time format
        if (dateTimeField.getText().isEmpty()) {
            // Add more sophisticated date/time validation if needed
            log.warn("No Date/Time inserted");
            showAlert("Warning","Date/Time empty","Please insert a Date/Time");
            return false;
        }

        // validate comment
        if (commentField.getText().isEmpty()) {
            // Add more sophisticated date/time validation if needed
            log.warn("No comment written");
            showAlert("Warning","Comment empty","Please write a short comment");
            return false;
        }

        // Validate total distance
        try {
            Double.parseDouble(totalDistanceField.getText());
        } catch (NumberFormatException e) {
            log.warn("No valid number inserted");
            showAlert("Warning","Total Distance is not a Number","Please insert a valid Number");
            return false;
        }

        // Validate total time
        try {
            Double.parseDouble(totalTimeField.getText());
        } catch (NumberFormatException e) {
            log.warn("No valid number inserted");
            showAlert("Warning","Total Time is not a Number","Please insert a valid Number");
            return false;
        }

        return true;
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
