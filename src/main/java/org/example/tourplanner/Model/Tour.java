package org.example.tourplanner.Model;


import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

@Data
@AllArgsConstructor

public class Tour {

    private String name;
    private String description;
    private String from;
    private String to;
    private String transportType;
    private double distance;
    private String estimatedTime;
    private Image image;

}
