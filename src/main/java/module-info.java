module org.example.projectj3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.projectj3 to javafx.fxml;
    exports org.example.projectj3;
    exports org.example.projectj3.entity;
    opens org.example.projectj3.entity to javafx.fxml;
    exports org.example.projectj3.tests;
    opens org.example.projectj3.tests to javafx.fxml;
    exports org.example.projectj3.GUI;
    opens org.example.projectj3.GUI to javafx.fxml;
}