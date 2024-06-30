module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires org.slf4j;
    requires kernel;
    requires layout;
    requires io;

    requires jdk.jsobject;

    requires javafx.graphics;
    requires java.desktop;

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.slf4j;
    requires com.google.gson;


    // Main package
    opens org.example.tourplanner to javafx.fxml, javafx.graphics;
    exports org.example.tourplanner;

    // UI package
    exports org.example.tourplanner.UI.controllers;
    opens org.example.tourplanner.UI.controllers to javafx.fxml;
    exports org.example.tourplanner.UI.viewmodel;
    opens org.example.tourplanner.UI.viewmodel to javafx.fxml;

    // BL package
    exports org.example.tourplanner.BL.Model;
    opens org.example.tourplanner.BL.Model to org.hibernate.orm.core, javafx.fxml;

    exports org.example.tourplanner.BL.Services;
    opens org.example.tourplanner.BL.Services to org.hibernate.orm.core;

    // DAL package
    exports org.example.tourplanner.DAL.repository;
    opens org.example.tourplanner.DAL.repository to org.hibernate.orm.core;

    //exports org.example.tourplanner.DAL.database;
    //opens org.example.tourplanner.DAL.database to org.hibernate.orm.core;

}