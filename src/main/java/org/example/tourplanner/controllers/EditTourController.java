package org.example.tourplanner.controllers;

import org.example.tourplanner.Model.Tour;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.tourplanner.viewmodel.TourViewModel;

import static org.example.tourplanner.viewmodel.TourViewModel.getViewModel;

public class EditTourController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private ComboBox<String> transportTypeComboBox;
    @FXML
    private TextField distanceField;
    @FXML
    private TextField estimatedTimeField;
    @FXML
    private Label placeholderLabel;

    private TourController mainController;
    private Stage dialogStage;
    private Tour tourToEdit;
    private TourViewModel tourViewModel;

    public EditTourController() {
        this.tourViewModel = getViewModel();
    }

    @FXML
    public void initialize() {
        transportTypeComboBox.getSelectionModel().selectFirst();
        placeholderLabel.setText("Placeholder for Route Image");
    }

    public void setMainController(TourController mainController) {
        this.mainController = mainController;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTourToEdit(Tour tour) {
        this.tourToEdit = tour;

        // populate the fields with the current values of the tour
        nameField.setText(tour.getName());
        descriptionField.setText(tour.getDescription());
        fromField.setText(tour.getFrom());
        toField.setText(tour.getTo());
        transportTypeComboBox.setValue(tour.getTransportType());
        distanceField.setText(String.valueOf(tour.getDistance()));
        estimatedTimeField.setText(tour.getEstimatedTime());
    }
    @FXML
    public void saveTour() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String from = fromField.getText();
        String to = toField.getText();
        String transportType = transportTypeComboBox.getValue();
        double distance;
        try {
            distance = Double.parseDouble(distanceField.getText());
        } catch (NumberFormatException e) {
            showAlert("Warnung", "Ungültige Distanz", "Bitte geben Sie eine gültige Distanz ein.");
            return;
        }
        String estimatedTime = estimatedTimeField.getText();

        if (!name.isEmpty() && !description.isEmpty() && !from.isEmpty() && !to.isEmpty() && !estimatedTime.isEmpty()) {
            tourViewModel.editTour(tourToEdit, name, description, from, to, transportType, distance, estimatedTime);
            dialogStage.close();
        } else {
            showAlert("Warnung", "Alle Felder sind erforderlich", "Bitte füllen Sie alle Felder aus.");
        }
    }

    @FXML
    public void cancel() {
        dialogStage.close();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}