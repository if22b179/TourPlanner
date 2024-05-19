package org.example.tourplanner.Model;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

@Data
@AllArgsConstructor

public class Tour {

    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty from;
    private SimpleStringProperty to;
    private SimpleStringProperty transportType;
    private SimpleDoubleProperty distance;
    private SimpleStringProperty estimatedTime;
    private SimpleObjectProperty<Image> image;

}
