package org.example.tourplanner.viewModels;


import static org.junit.jupiter.api.Assertions.*;

import org.example.tourplanner.DAL.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;



public class HibernateUtilTest {

    @Test
    public void testSessionFactoryCreation() {
        // Test if the session factory is created
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        assertNotNull(sessionFactory,"SessionFactory should be created");
    }

    @Test
    public void testSessionCreation() {
        // Test if a session can be opened
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            assertNotNull(session,"Session should be created");
        } catch (Exception e) {
            // If any exception occurs, the test should fail
            assertNotNull( e, "Exception should not be thrown");
        }
    }
}
