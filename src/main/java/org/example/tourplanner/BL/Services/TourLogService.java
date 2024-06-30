package org.example.tourplanner.BL.Services;

import org.example.tourplanner.BL.Model.TourLog;
import org.example.tourplanner.DAL.repository.TourLogDAO;

import java.util.List;

public class TourLogService {

    private final TourLogDAO tourLogDAO;

    public TourLogService() {
        this.tourLogDAO = new TourLogDAO();
    }

    public void addTourLog(TourLog tourLog) {
        tourLogDAO.save(tourLog);
    }


    public void deleteTourLog(TourLog tourLog) {
        tourLogDAO.delete(tourLog);
    }


    public void updateTourLog(TourLog tourLog) {
        tourLogDAO.update(tourLog);
    }

    public List<TourLog> getTourLogs(int id){
        return tourLogDAO.getLogsForTour(id);
    }
}
