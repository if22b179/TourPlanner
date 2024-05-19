package org.example.tourplanner.Model;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.Data;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tour {

    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty from;
    private SimpleStringProperty to;
    private SimpleStringProperty transportType;
    private SimpleDoubleProperty distance;
    private SimpleStringProperty estimatedTime;
    private SimpleObjectProperty<Image> image;
    private ObservableList<TourLog> tourLogs;

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getFrom() {
        return from.get();
    }

    public SimpleStringProperty fromProperty() {
        return from;
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public String getTo() {
        return to.get();
    }

    public SimpleStringProperty toProperty() {
        return to;
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public String getTransportType() {
        return transportType.get();
    }

    public SimpleStringProperty transportTypeProperty() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType.set(transportType);
    }

    public double getDistance() {
        return distance.get();
    }

    public SimpleDoubleProperty distanceProperty() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance.set(distance);
    }

    public String getEstimatedTime() {
        return estimatedTime.get();
    }

    public SimpleStringProperty estimatedTimeProperty() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime.set(estimatedTime);
    }

    public Image getImage() {
        return image.get();
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

    public Tour(SimpleStringProperty name,
                SimpleStringProperty description,
                SimpleStringProperty from,
                SimpleStringProperty to,
                SimpleStringProperty transportType,
                SimpleDoubleProperty distance,
                SimpleStringProperty estimatedTime,
                SimpleObjectProperty<Image> image) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.image = image;
        this.tourLogs = FXCollections.observableArrayList();
    }
}
