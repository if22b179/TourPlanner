package org.example.tourplanner.UI.controllers;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.BL.Model.TourLog;
import org.example.tourplanner.BL.Services.PDFService;
import org.example.tourplanner.UI.viewmodel.TourViewModel;


import java.io.IOException;
import java.util.List;


import static org.example.tourplanner.UI.viewmodel.TourViewModel.getViewModel;

@Slf4j
public class TourController {
    @FXML private ListView<Tour> tourListView;
    @FXML private TableView<TourLog> tourLogTable;
    @FXML private TableColumn<TourLog, String> dateTimeColumn;
    @FXML private TableColumn<TourLog, String> commentColumn;
    @FXML private TableColumn<TourLog, Integer> difficultyColumn;
    @FXML private TableColumn<TourLog, Double> distanceColumn;
    @FXML private TableColumn<TourLog, String> timeColumn;
    @FXML private TableColumn<TourLog, Integer> ratingColumn;
    @FXML
    private Label placeholderLabel;

    private TourViewModel tourViewModel;
    private final PDFService pdfService;

    private boolean isDarkMode = false;


    public TourController() {
        this.tourViewModel = getViewModel();
        this.pdfService = PDFService.getPDFService();
    }

    @FXML
    public void initialize() {
        // Initialisieren der ListView für Touren
        tourListView.setItems(tourViewModel.getTours());
        tourListView.setCellFactory(param -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour tour, boolean empty) {
                super.updateItem(tour, empty);
                if (empty || tour == null) {
                    setText(null);
                } else {
                    setText(tour.getName());
                }
            }
        });

        // Listener hinzufügen, um die Tour Logs der ausgewählten Tour anzuzeigen
        tourListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                refreshTourLogs();
            } else {
                tourLogTable.setItems(null);
            }
        });

        // Initialisieren der TableColumns für Tour Logs
        dateTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateTime()));
        commentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        difficultyColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDifficulty()));
        distanceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalDistance()));
        timeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalTime()).asString());
        ratingColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRating()));

        // Placeholder für das Routenbild
        placeholderLabel.setText("Placeholder for Route Image");
    }

    @FXML
    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        applyDarkMode();
    }

    private void applyDarkMode() {
        Scene scene = tourListView.getScene(); // Get the current scene
        String darkModeCSS = getClass().getResource("/org/example/tourplanner/css/darkmode.css") != null ?
                getClass().getResource("/org/example/tourplanner/css/darkmode.css").toExternalForm() : null;
        if (darkModeCSS == null) {
            log.error("darkmode.css not found. Make sure the file is in the correct resources directory.");
            return;
        }
        if (isDarkMode) {
            scene.getStylesheets().add(darkModeCSS);
        } else {
            scene.getStylesheets().remove(darkModeCSS);
        }
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
            log.error("Failed to load addTourWindow.fxml", e);
        }
    }

    @FXML
    public void editTour() {
        try {
            Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour == null) {
                log.warn("No tour selected for editing.");
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
            editTourController.setTourToEdit(selectedTour);

            dialogStage.showAndWait();
        } catch (IOException e) {
            log.error("Failed to load editTourWindow.fxml", e);
        }
    }

    @FXML
    public void deleteTour() {
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            int index = tourListView.getSelectionModel().getSelectedIndex();
            tourListView.getItems().remove(index);
            tourViewModel.removeTour(selectedTour);
            log.debug("Deleted tour: {}", selectedTour.getName());
        } else {
            log.warn("No tour selected for deletion.");
            showAlert("Warnung", "Keine Tour ausgewählt", "Bitte wählen Sie eine Tour aus der Liste aus, bevor Sie löschen.");
        }
    }

    @FXML
    public void tourDetails() {
        try {
            Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour == null) {
                log.warn("No tour selected for viewing details.");
                showAlert("Warnung", "Keine Tour ausgewählt", "Bitte wählen Sie eine Tour aus der Liste aus, bevor Sie bearbeiten.");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/tourDetailsWindow.fxml"));
            Scene scene = new Scene(loader.load(), 500, 350);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Tour details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(tourListView.getScene().getWindow());
            dialogStage.setScene(scene);

            EditTourController editTourController = loader.getController();
            editTourController.setMainController(this);
            editTourController.setDialogStage(dialogStage);
            editTourController.setTourToEdit(selectedTour);

            dialogStage.showAndWait();
        } catch (IOException e) {
            log.error("Failed to load tourDetailsWindow.fxml", e);
        }
    }

    @FXML public void addTourLog() {
        if (isTourSelected()) {
            openTourLogWindow(null);
            refreshTourLogs();
        }
    }

    @FXML public void editTourLog() {
        if (isTourSelected()) {
            TourLog selectedLog = tourLogTable.getSelectionModel().getSelectedItem();
            if (selectedLog != null) {
                openTourLogWindow(selectedLog);
            } else {
                log.warn("No log selected for editing.");
                showAlert("No Log Selected", "Please select a log", "You must select a log to edit.");
            }
        }
        refreshTourLogs();
    }

    @FXML public void deleteTourLog() {
        if (isTourSelected()) {
            TourLog selectedLog = tourLogTable.getSelectionModel().getSelectedItem();
            if (selectedLog != null) {
                Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
                tourViewModel.removeTourLog(selectedTour, selectedLog);
                refreshTourLogs();
                log.debug("Deleted tour log: {}", selectedLog);
            } else {
                log.warn("No log selected for deletion.");
                showAlert("No Log Selected", "Please select a log", "You must select a log to delete.");
            }
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openTourLogWindow(TourLog tourLog) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/tourLogWindow.fxml"));
            Parent root = loader.load();

            TourLogController controller = loader.getController();
            controller.setTour(tourListView.getSelectionModel().getSelectedItem());
            controller.setTourViewModel(tourViewModel);
            if (tourLog != null) {
                controller.setTourLog(tourLog);
            }

            Stage stage = new Stage();
            stage.setTitle(tourLog == null ? "Add Tour Log" : "Edit Tour Log");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            log.error("failed to open tour log window", e);
        }
    }

    private boolean isTourSelected() {
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
        if (selectedTour == null) {
            showAlert("No Tour Selected", "Please select a tour", "You must select a tour to proceed.");
            return false;
        }
        return true;
    }

    @FXML
    public void generatePDF(ActionEvent actionEvent) {
        try{
            pdfService.createTourListPDF("src/main/resources/TourList.pdf");
            System.out.println("PDF generated successfully.");
        } catch (Exception e) {
            log.error("failed to generate pdf", e);
            throw new RuntimeException(e);
        }
    }

    private void refreshTourLogs() {
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            List<TourLog> tourLogs = tourViewModel.getTourLogs(selectedTour.getId());
            ObservableList<TourLog> observableTourLogs = FXCollections.observableArrayList(tourLogs);
            tourLogTable.setItems(observableTourLogs);
        } else {
            tourLogTable.setItems(FXCollections.observableArrayList());
        }
    }

}
