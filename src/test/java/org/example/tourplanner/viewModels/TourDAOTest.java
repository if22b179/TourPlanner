package org.example.tourplanner.viewModels;

import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.DAL.repository.TourDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TourDAOTest {

    private static TourDAO tourDAO;
    private static Tour testTour;

    @BeforeAll
    public static void setup() {
        tourDAO = new TourDAO();
        testTour = tourDAO.findByName("Test Tour");
    }

    @Test
    public void testSaveTour() {
        try {
            tourDAO.save(testTour);
        } catch (Exception e) {
            fail("Failed to save tour: " + e.getMessage());
        }
    }

    @Test void findByNameTest(){
        try{
            Tour tmp = tourDAO.findByName(testTour.getName());
            assertEquals("Test Tour", tmp.getName());
        } catch (Exception e){
            fail("failed to find " + e.getMessage());
        }
    }

    @Test
    public void testUpdateTour() {
        try {
            testTour.setDescription("Updated Description");
            tourDAO.update(testTour);
            Tour updatedTour = tourDAO.findByName(testTour.getName());
            assertEquals("Updated Description", updatedTour.getDescription(), "Tour description should be updated");
        } catch (Exception e) {
            fail("Failed to update tour: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteTour() {
        try {
            tourDAO.delete(testTour);
            Tour deletedTour = tourDAO.findByName(testTour.getName());
            assertNull(deletedTour, "Tour should be deleted");
        } catch (Exception e) {
            fail("Failed to delete tour: " + e.getMessage());
        }
    }

    @Test
    public void testFindAllTours() {
        try {
            List<Tour> tours = tourDAO.findAll();
            assertNotNull(tours, "The tour list should not be null");
            assertTrue(tours.size() > 0, "The tour list should not be empty");
            System.out.println("Number of tours found: " + tours.size());
        } catch (Exception e) {
            fail("Failed to find all tours: " + e.getMessage());
        }
    }

    @AfterAll
    public static void tearDown() {
        // Cleanup logic if needed
    }
}