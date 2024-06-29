package org.example.tourplanner.DAL.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.DAL.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
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
            log.error("Failed to save {} in Database", entity.getClass().getSimpleName(), e);
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
            log.error("Failed to update {} in Database", entity.getClass().getSimpleName(), e);
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
            log.error("Failed to delete {} in Database", entity.getClass().getSimpleName(), e);
        }
    }

}
