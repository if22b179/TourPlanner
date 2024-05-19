module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.controlsfx.controls;

    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;

    exports org.example.tourplanner.controllers;
    opens org.example.tourplanner.controllers to javafx.fxml;
    exports org.example.tourplanner.viewmodel;
    opens org.example.tourplanner.viewmodel to javafx.fxml;

    exports org.example.tourplanner.Model;
    opens org.example.tourplanner.Model to javafx.fxml;
}