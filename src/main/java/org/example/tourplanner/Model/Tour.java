package org.example.tourplanner.Model;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

@Data

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
