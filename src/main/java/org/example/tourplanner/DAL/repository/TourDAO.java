package org.example.tourplanner.DAL.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class TourDAO extends BaseCrudDAO<Tour> {

    @Override
    public void save(Tour tour) {
        System.out.println("bin in save methode kurz vorm save");
        try {
            System.out.println("im try jz gleich save");
            super.save(tour);
            System.out.println("nach save");
        } catch (Exception e) {
            log.error("Failed to save tour with id {}", tour.getId(), e);
        }
    }

    @Override
    public void update(Tour tour) {
        try {
            super.update(tour);
        } catch (Exception e) {
            log.error("Failed to update tour with id {}", tour.getId(), e);
        }
    }

    @Override
    public void delete(Tour tour) {
        try {
            super.delete(tour);
        } catch (Exception e) {
            log.error("Failed to delete tour with id {}", tour.getId(), e);
        }
    }

    public Tour findByName(String name) {
        try (Session session = getSession()) {
            Query<Tour> query = session.createQuery("FROM Tour WHERE name = :name", Tour.class);
            query.setParameter("name", name);
            Tour tour = query.uniqueResult();
            if (tour != null) {
                Hibernate.initialize(tour.getTourLogs());
            }
            return tour;
        } catch (Exception e) {
            log.error("Failed to find tour with name {}", name, e);
            return null;
        }
    }

    public List<Tour> findAll() {
        try (Session session = getSession()) {
            Query<Tour> query = session.createQuery("FROM Tour", Tour.class);
            List<Tour> tours = query.list();
            for (Tour tour : tours) {
                Hibernate.initialize(tour.getTourLogs());
            }
            return tours;
        } catch (Exception e) {
            log.error("Failed to find tours with id", e);
            e.printStackTrace();
            return null;
        }
    }
}
