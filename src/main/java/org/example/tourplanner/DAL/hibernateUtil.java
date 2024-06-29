package org.example.tourplanner.DAL;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.BL.Model.TourLog;
import lombok.Getter;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


@Slf4j
public class hibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            log.debug("Initializing Hibernate SessionFactory...");
            // Load the configuration and ensure the file is found
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            // Add annotated classes explicitly
            configuration.addAnnotatedClass(Tour.class);
            configuration.addAnnotatedClass(TourLog.class);
            // Add other model classes as needed
            // configuration.addAnnotatedClass(AnotherModel.class);

            sessionFactory = configuration.buildSessionFactory(builder.build());

            log.info("Hibernate SessionFactory initialized successfully.");
        } catch (Exception e) {
            log.error("Failed to initialize Hibernate SessionFactory.", e);
            throw new RuntimeException(e);
        }
    }
}
