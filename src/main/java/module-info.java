module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.controlsfx.controls;

    // Main package
    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;

    // UI package
    exports org.example.tourplanner.UI.controllers;
    opens org.example.tourplanner.UI.controllers to javafx.fxml;
    exports org.example.tourplanner.UI.viewmodel;
    opens org.example.tourplanner.UI.viewmodel to javafx.fxml;

    // BL package
    exports org.example.tourplanner.BL.Model;
    opens org.example.tourplanner.BL.Model to javafx.fxml;
}
