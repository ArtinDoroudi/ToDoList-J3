module org.example.projectj3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.projectj3 to javafx.fxml;
    exports org.example.projectj3;
}