package org.example.tourplanner.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourLog {
    private SimpleStringProperty dateTime;
    private SimpleStringProperty comment;
    private SimpleIntegerProperty difficulty;
    private SimpleDoubleProperty totalDistance;
    private SimpleStringProperty totalTime;
    private SimpleIntegerProperty rating;
}
