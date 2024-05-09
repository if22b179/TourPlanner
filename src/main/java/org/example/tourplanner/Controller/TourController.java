package org.example.tourplanner.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        // Platzhalter für die Funktion zum Hinzufügen einer Tour
    }

    @FXML
    public void editTour() {
        // Platzhalter für die Funktion zum Bearbeiten einer Tour
    }

    @FXML
    public void deleteTour() {
        // Platzhalter für die Funktion zum Löschen einer Tour
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
}
