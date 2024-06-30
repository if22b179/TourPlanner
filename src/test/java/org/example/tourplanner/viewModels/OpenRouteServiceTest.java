package org.example.tourplanner.viewModels;

import org.example.tourplanner.BL.Model.RouteInfo;
import org.example.tourplanner.BL.Model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.example.tourplanner.BL.Services.OpenRouteService;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OpenRouteServiceTest {

    private OpenRouteService openRouteService;

    @Mock
    private Tour mockTour;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        openRouteService = new OpenRouteService();
    }

    @Test
    void testGetRoute() throws Exception {
        // Mock the startHttpRequest method to return a predefined JSON response
        OpenRouteService openRouteServiceSpy = spy(openRouteService);
        String jsonResponse = "{\"features\":[{\"properties\":{\"summary\":{\"distance\":10000,\"duration\":6000}}}]}";
        doReturn(jsonResponse).when(openRouteServiceSpy).startHttpRequest(anyString());

        String response = openRouteServiceSpy.getRoute("48.2082", "16.3738", "47.4979", "19.0402", "driving-car");
        assertNotNull(response);
        assertEquals(jsonResponse, response);
    }

    @Test
    void testGeocodeAddress() throws Exception {
        // Mock the startHttpRequest method to return a predefined JSON response
        OpenRouteService openRouteServiceSpy = spy(openRouteService);
        String jsonResponse = "{\"features\":[{\"geometry\":{\"coordinates\":[16.3738,48.2082]}}]}";
        doReturn(jsonResponse).when(openRouteServiceSpy).startHttpRequest(anyString());

        double[] coords = openRouteServiceSpy.geocodeAddress("Vienna, Austria");
        assertNotNull(coords);
        assertEquals(48.2082, coords[0]);
        assertEquals(16.3738, coords[1]);
    }

    @Test
    void testGetInfo() {
        String jsonResponse = "{\"features\":[{\"properties\":{\"summary\":{\"distance\":10000,\"duration\":6000}}}]}";
        RouteInfo routeInfo = openRouteService.getInfo(jsonResponse);
        assertNotNull(routeInfo);
        assertEquals(10.0, routeInfo.getDistance());
        assertEquals(100.0, routeInfo.getDuration());
    }

    @Test
    void testGetMap() throws Exception {
        when(mockTour.getFrom()).thenReturn("Vienna, Austria");
        when(mockTour.getTo()).thenReturn("Budapest, Hungary");

        // Mock the geocodeAddress method to return predefined coordinates
        OpenRouteService openRouteServiceSpy = spy(openRouteService);
        doReturn(new double[]{48.2082, 16.3738}).when(openRouteServiceSpy).geocodeAddress("Vienna, Austria");
        doReturn(new double[]{47.4979, 19.0402}).when(openRouteServiceSpy).geocodeAddress("Budapest, Hungary");

        BufferedImage map = openRouteServiceSpy.getMap(mockTour, 10, 10);
        assertNotNull(map);
    }

    @Test
    void testSaveImage() throws IOException {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        String imagePath = openRouteService.saveImage(image);
        assertNotNull(imagePath);
        assertTrue(Files.exists(Path.of(imagePath)));
    }


    @Test
    void testGetRouteWithInvalidCoordinates() throws Exception {
        OpenRouteService openRouteServiceSpy = spy(openRouteService);
        doThrow(new RuntimeException("Failed : HTTP error code : 400")).when(openRouteServiceSpy).startHttpRequest(anyString());

        Exception exception = assertThrows(Exception.class, () -> {
            openRouteServiceSpy.getRoute("invalid", "coordinates", "invalid", "coordinates", "driving-car");
        });

        assertEquals("Failed : HTTP error code : 400", exception.getMessage());
    }

    @Test
    void testGeocodeAddressWithInvalidAddress() throws Exception {
        OpenRouteService openRouteServiceSpy = spy(openRouteService);
        String jsonResponse = "{\"features\":[]}";
        doReturn(jsonResponse).when(openRouteServiceSpy).startHttpRequest(anyString());

        Exception exception = assertThrows(Exception.class, () -> {
            openRouteServiceSpy.geocodeAddress("Invalid Address");
        });

        assertEquals("No coordinates found for the address: Invalid Address", exception.getMessage());
    }

    @Test
    void testGetInfoWithErrorInResponse() {
        String jsonResponse = "{\"error\":\"Invalid API key\"}";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            openRouteService.getInfo(jsonResponse);
        });

        assertEquals("API Error: Invalid API key", exception.getMessage());
    }

    @Test
    void testGetMapWithInvalidAddresses() throws Exception {
        when(mockTour.getFrom()).thenReturn("Invalid Address 1");
        when(mockTour.getTo()).thenReturn("Invalid Address 2");

        OpenRouteService openRouteServiceSpy = spy(openRouteService);
        doThrow(new RuntimeException("No coordinates found for the address: Invalid Address 1"))
                .when(openRouteServiceSpy).geocodeAddress("Invalid Address 1");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            openRouteServiceSpy.getMap(mockTour, 10, 10);
        });

        assertEquals("No coordinates found for the address: Invalid Address 1", exception.getMessage());
    }
}
