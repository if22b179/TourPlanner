package org.example.tourplanner.UI.controllers;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.tourplanner.BL.Services.PDFService;
import org.example.tourplanner.UI.viewmodel.TourViewModel;

import java.net.URL;
import java.nio.file.Paths;

import static org.example.tourplanner.UI.viewmodel.TourViewModel.getViewModel;
@Slf4j
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
    private ImageView mapImageView;

    private TourController mainController;
    private Stage dialogStage;
    private Tour tourToEdit;
    private TourViewModel tourViewModel;

    private final PDFService pdfService;

    public EditTourController() {
        this.tourViewModel = getViewModel();
        this.pdfService = PDFService.getPDFService();
    }

    @FXML
    public void initialize() {
        transportTypeComboBox.getSelectionModel().selectFirst();
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
        String imageUrl = tour.getImage();
        log.info("Image URL from tour: {}", imageUrl);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                // Anpassung des Pfads
                String resourcePath = "/Images/" + Paths.get(imageUrl).getFileName().toString();
                URL imageResource = getClass().getResource(resourcePath);
                if (imageResource != null) {
                    Image image = new Image(imageResource.toExternalForm());
                    mapImageView.setImage(image);
                    log.info("Image successfully loaded: {}", imageResource.toExternalForm());
                } else {
                    log.error("Image resource not found: {}", resourcePath);
                }
            } catch (Exception e) {
                log.error("Error loading image from URL: {}", imageUrl, e);
            }
        }
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
            log.warn("Invalid distance input: {}", distanceField.getText(), e);
            showAlert("Warnung", "Ungültige Distanz", "Bitte geben Sie eine gültige Distanz ein.");
            return;
        }
        String estimatedTime = estimatedTimeField.getText();

        if (!name.isEmpty() && !description.isEmpty() && !from.isEmpty() && !to.isEmpty() && !estimatedTime.isEmpty()) {
            tourViewModel.editTour(tourToEdit, name, description, from, to, transportType, distance, estimatedTime);
            log.info("Tour updated: {}", tourToEdit);
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

    @FXML
    public void generateTourPDF(ActionEvent actionEvent) {
        String filename = nameField.getText() + ".pdf";
        try{
            pdfService.createTourPDF("src/main/resources/" + filename, nameField.getText());
            System.out.println("PDF generated successfully.");
        } catch (Exception e) {
            log.error("failed to generate pdf", e);
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}