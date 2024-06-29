package org.example.tourplanner.BL.Services;

public interface IPDFService {
    void createTourListPDF(String filePath) throws Exception;
    void createTourPDF(String filePath, String name) throws Exception;
}
