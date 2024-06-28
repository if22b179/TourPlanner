package org.example.tourplanner.DAL.repository;

import org.example.tourplanner.BL.Model.Tour;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class TourDAO extends BaseCrudDAO<Tour> {

    @Override
    public void save(Tour tour) {
        System.out.println("bin in save methode kurz vorm save");
        try {
            System.out.println("im try jz gleich save");
            super.save(tour);
            System.out.println("nach save");
        } catch (Exception e) {
            System.out.println("Failed to save tour with id in TourDAO Class {}");
        }
    }

    @Override
    public void update(Tour tour) {
        try {
            super.update(tour);
        } catch (Exception e) {
            System.out.println("Failed to update tour with id {}");
        }
    }

    @Override
    public void delete(Tour tour) {
        try {
            super.delete(tour);
        } catch (Exception e) {
            System.out.println("Failed to delete tour with id {}");
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
            System.out.println("Failed to find tour with name {}");
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
            System.out.println("Failed to find all tours: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
