package org.example.tourplanner.DAL.repository;

import org.example.tourplanner.DAL.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BaseCrudDAO<T> {

    protected Session getSession() {
        return hibernateUtil.getSessionFactory().openSession();
    }

    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed to save {} in Database");
        }
    }

    protected void update(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed to update {} in Database");
        }
    }

    protected void delete(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed to delete {} in Database");
        }
    }

}
