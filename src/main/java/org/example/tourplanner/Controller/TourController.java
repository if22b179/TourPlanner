package org.example.tourplanner.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.tourplanner.HelloApplication;
import org.example.tourplanner.Model.Tour;

import java.io.IOException;

public class TourController {
    @FXML
    private ListView<String> tourListView;
    @FXML
    private TableView<Object> tourLogTable;
    @FXML
    private Label placeholderLabel;

    @FXML
    public void initialize() {
        // Beispiel-Daten
        tourListView.getItems().addAll("Wienerwald", "Dopplerhütte", "Figlwarte", "Dorfrunde");

        TableColumn<Object, String> dateColumn = new TableColumn<>("Date");
        TableColumn<Object, String> durationColumn = new TableColumn<>("Duration");
        TableColumn<Object, String> distanceColumn = new TableColumn<>("Distance");

        tourLogTable.getColumns().addAll(dateColumn, durationColumn, distanceColumn);
        placeholderLabel.setText("Placeholder for Route Image");
    }

    @FXML
    public void addTour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/addTourWindow.fxml"));
            Scene scene = new Scene(loader.load(), 500, 350);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Neue Tour hinzufügen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(tourListView.getScene().getWindow());
            dialogStage.setScene(scene);

            AddTourController addTourController = loader.getController();
            addTourController.setMainController(this);
            addTourController.setDialogStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editTour() {
        try {
            String selectedTour = tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour == null) {
                showAlert("Warnung", "Keine Tour ausgewählt", "Bitte wählen Sie eine Tour aus der Liste aus, bevor Sie bearbeiten.");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/editTourWindow.fxml"));
            Scene scene = new Scene(loader.load(), 500, 350);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Tour verändern");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(tourListView.getScene().getWindow());
            dialogStage.setScene(scene);

            EditTourController editTourController = loader.getController();
            editTourController.setMainController(this);
            editTourController.setDialogStage(dialogStage);
            editTourController.saveTour();

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteTour() {
        String selectedTour = tourListView.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            int index = tourListView.getSelectionModel().getSelectedIndex();
            tourListView.getItems().remove(index);
        } else {
            showAlert("Warnung", "Keine Tour ausgewählt", "Bitte wählen Sie eine Tour aus der Liste aus, bevor Sie löschen.");
        }
    }

    @FXML
    public void addTourLog() {
        // Platzhalter für die Funktion zum Hinzufügen eines Tour-Logs
    }

    @FXML
    public void editTourLog() {
        // Platzhalter für die Funktion zum Bearbeiten eines Tour-Logs
    }

    @FXML
    public void deleteTourLog() {
        // Platzhalter für die Funktion zum Löschen eines Tour-Logs
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
