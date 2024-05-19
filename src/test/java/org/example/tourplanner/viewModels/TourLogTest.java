package org.example.tourplanner.viewModels;

import static org.junit.jupiter.api.Assertions.*;

import javafx.beans.property.SimpleIntegerProperty;
import org.example.tourplanner.Model.TourLog;
import org.junit.jupiter.api.Test;

public class TourLogTest {

    @Test
    public void testTourLogCreation() {
        // Testdaten
        String dateTime = "2023-05-18T10:15:30";
        String comment = "Great hike, beautiful weather.";
        String difficulty = "3";
        String totalDistance = "12.5";
        String totalTime = "5.0";
        String rating = "4";

        // TourLog-Objekt erstellen
        TourLog tourLog = new TourLog(dateTime, comment, difficulty , totalDistance, totalTime, rating);

        // Überprüfen, ob das TourLog korrekt erstellt wurde
        assertEquals(dateTime, tourLog.getDateTime());
        assertEquals(comment, tourLog.getComment());
        assertEquals(3, tourLog.getDifficulty());
        assertEquals(12.5, tourLog.getTotalDistance());
        assertEquals(5.0, tourLog.getTotalTime());
        assertEquals(4, tourLog.getRating());
    }

    @Test
    public void testTourLogModification() {
        // TourLog-Objekt erstellen
        TourLog tourLog = new TourLog("Old DateTime", "Old Comment", "2", "10.0", "4.0", "3");

        // Änderungen vornehmen
        tourLog.setDateTime("New DateTime");
        tourLog.setComment("New Comment");
        tourLog.setDifficulty(4);
        tourLog.setTotalDistance(15.0);
        tourLog.setTotalTime(6.0);
        tourLog.setRating(5);

        // Überprüfen, ob die Änderungen korrekt übernommen wurden
        assertEquals("New DateTime", tourLog.getDateTime());
        assertEquals("New Comment", tourLog.getComment());
        assertEquals(4, tourLog.getDifficulty());
        assertEquals(15.0, tourLog.getTotalDistance());
        assertEquals(6.0, tourLog.getTotalTime());
        assertEquals(5, tourLog.getRating());
    }
}

