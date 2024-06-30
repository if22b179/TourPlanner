package org.example.tourplanner.DAL.repository;

import org.example.tourplanner.BL.Model.TourLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


@Slf4j
public class TourLogDAO extends BaseCrudDAO<TourLog> {

    @Override
    public void save(TourLog tourLog) {
        try {
            super.save(tourLog);
        } catch (Exception e) {
            log.error("Failed to save tour with id {}", tourLog.getId(), e);
        }
    }

    @Override
    public void update(TourLog tourLog) {
        try {
            super.update(tourLog);
        } catch (Exception e) {
            log.error("Failed to update tour with id {}", tourLog.getId(), e);
        }
    }

    @Override
    public void delete(TourLog tourLog) {
        try {
            super.delete(tourLog);
        } catch (Exception e) {
            log.error("Failed to delete tour with id {}", tourLog.getId(), e);
        }
    }
    public List<TourLog> getLogsForTour(int tourId) {
        try (Session session = getSession()) {
            Query<TourLog> query = session.createQuery("FROM TourLog WHERE tour_id = :tourId");
            query.setParameter("tourId", tourId);
            return query.list();
        } catch (Exception e) {
            log.error("Failed to load tour logs for tour with id {}", tourId, e);
            return null;
        }
    }


}
