module com.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jooq;
    requires javafx.graphics;
    requires MaterialFX;
    opens com.example.demofx to javafx.fxml;
    exports com.example.demofx;
    exports com.example.demofx.databaseManger.jooq.tables;
    opens com.example.demofx.databaseManger.jooq.tables to javafx.fxml;
    exports com.example.demofx.databaseManger.jooq.tables.records;
    opens com.example.demofx.databaseManger.jooq.tables.records to javafx.fxml;
    exports com.example.demofx.databaseManger;
    opens com.example.demofx.databaseManger to javafx.fxml;
    exports com.example.demofx.controller;
    opens com.example.demofx.controller to javafx.fxml;
}