package org.example.tourplanner.DAL;
import org.example.tourplanner.BL.Model.Tour;
import org.example.tourplanner.BL.Model.TourLog;
import lombok.Getter;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
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
        } catch (Exception e) {
            System.out.println("Connection zu DB failed");
            throw new RuntimeException(e);
        }
    }
}
