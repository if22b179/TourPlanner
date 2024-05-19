package org.example.tourplanner.Model;

import javafx.beans.property.*;

public class TourLog {
    private final StringProperty dateTime;
    private final StringProperty comment;
    private final IntegerProperty difficulty;
    private final DoubleProperty totalDistance;
    private final DoubleProperty totalTime;
    private final IntegerProperty rating;

    public TourLog(String dateTime, String comment, String difficulty, String totalDistance, String totalTime, String rating) {
        this.dateTime = new SimpleStringProperty(dateTime);
        this.comment = new SimpleStringProperty(comment);
        this.difficulty = new SimpleIntegerProperty(Integer.parseInt(difficulty));
        this.totalDistance = new SimpleDoubleProperty(Double.parseDouble(totalDistance));
        this.totalTime = new SimpleDoubleProperty(Double.parseDouble(totalTime));
        this.rating = new SimpleIntegerProperty(Integer.parseInt(rating));
    }

    // Getter-Methoden
    public String getDateTime() {
        return dateTime.get();
    }

    public String getComment() {
        return comment.get();
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public double getTotalDistance() {
        return totalDistance.get();
    }

    public double getTotalTime() {
        return totalTime.get();
    }

    public int getRating() {
        return rating.get();
    }

    // Setter-Methoden
    public void setDateTime(String dateTime) {
        this.dateTime.set(dateTime);
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public void setDifficulty(int difficulty) {
        this.difficulty.set(difficulty);
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance.set(totalDistance);
    }

    public void setTotalTime(double totalTime) {
        this.totalTime.set(totalTime);
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    // Property-Getter für Bindungen
    public StringProperty dateTimeProperty() {
        return dateTime;
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public IntegerProperty difficultyProperty() {
        return difficulty;
    }

    public DoubleProperty totalDistanceProperty() {
        return totalDistance;
    }

    public DoubleProperty totalTimeProperty() {
        return totalTime;
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }
}
