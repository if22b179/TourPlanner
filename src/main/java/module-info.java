module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    requires org.controlsfx.controls;
    opens org.example.tourplanner.Controller to javafx.fxml;


    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;
}