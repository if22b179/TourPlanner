package org.example.tourplanner.UI.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.tourplanner.UI.viewmodel.TourViewModel;

import static org.example.tourplanner.UI.viewmodel.TourViewModel.getViewModel;
@Slf4j
public class AddTourController {
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
    private TourViewModel tourViewModel;

    public AddTourController() {
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

    @FXML
    public void saveTour() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String from = fromField.getText();
        String to = toField.getText();
        String transportType = transportTypeComboBox.getSelectionModel().getSelectedItem();
        double distance;
        try {
            distance = Double.parseDouble(distanceField.getText());
        } catch (NumberFormatException e) {
            log.warn("Invalid distance input: {}", distanceField.getText(), e);
            showAlert("Warnung", "Ungültige Distanz", "Bitte geben Sie eine gültige Distanz ein.");
            return;
        }
        String estimatedTime = estimatedTimeField.getText();

        if (!name.isEmpty() && !description.isEmpty() && !from.isEmpty() && !to.isEmpty() && !estimatedTime.isEmpty()) {
            Tour newTour = new Tour(name,
                                    description,
                                    from,
                                    to,
                                    transportType,
                                    distance,
                                    estimatedTime,
                                    null);
            tourViewModel.addTour(newTour);
            log.info("Tour saved: {}", newTour);
            dialogStage.close();
        } else {
            log.warn("Failed to save tour: not all fields are filled.");
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