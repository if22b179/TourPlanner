package org.example.tourplanner.Controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.tourplanner.Model.Tour;
import org.example.tourplanner.Model.TourLog;
import org.example.tourplanner.viewModels.TourViewModel;

public class TourLogController {
    @FXML
    private TextField dateTimeField;
    @FXML
    private TextArea commentField;
    @FXML
    private TextField difficultyField;
    @FXML
    private TextField totalDistanceField;
    @FXML
    private TextField totalTimeField;
    @FXML
    private TextField ratingField;

    private TourLog tourLog;
    private Tour tour;
    private TourViewModel tourViewModel;

    public void setTourLog(TourLog tourLog) {
        this.tourLog = tourLog;
        if (tourLog != null) {
            dateTimeField.setText(tourLog.getDateTime().toString());
            commentField.setText(tourLog.getComment().get());
            difficultyField.setText(String.valueOf(tourLog.getDifficulty().get()));
            totalDistanceField.setText(String.valueOf(tourLog.getTotalDistance().get()));
            totalTimeField.setText(tourLog.getTotalTime().get());
            ratingField.setText(String.valueOf(tourLog.getRating().get()));
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
        if (tourLog == null) {
            // Creating a new TourLog
            TourLog newLog = new TourLog(
                    new SimpleStringProperty(dateTimeField.getText()),
                    new SimpleStringProperty(commentField.getText()),
                    new SimpleIntegerProperty(Integer.parseInt(difficultyField.getText())),
                    new SimpleDoubleProperty(Double.parseDouble(totalDistanceField.getText())),
                    new SimpleStringProperty(totalTimeField.getText()),
                    new SimpleIntegerProperty(Integer.parseInt(ratingField.getText()))
            );
            tourViewModel.addTourLog(tour, newLog);
        } else {
            // Editing an existing TourLog
            tourLog.setDateTime(new SimpleStringProperty(dateTimeField.getText()));
            tourLog.setComment(new SimpleStringProperty(commentField.getText()));
            tourLog.setDifficulty(new SimpleIntegerProperty(Integer.parseInt(difficultyField.getText())));
            tourLog.setTotalDistance(new SimpleDoubleProperty(Double.parseDouble(totalDistanceField.getText())));
            tourLog.setTotalTime(new SimpleStringProperty(totalTimeField.getText()));
            tourLog.setRating(new SimpleIntegerProperty(Integer.parseInt(ratingField.getText())));

        }
        // Close the window after saving
        ((Stage) dateTimeField.getScene().getWindow()).close();
    }
}
