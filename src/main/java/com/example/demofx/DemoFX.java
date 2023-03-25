package com.example.demofx;

import com.example.demofx.controller.LoginController;
import com.example.demofx.databaseManger.DataBaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;

import static com.example.demofx.databaseManger.DataBaseConnection.con;


public class DemoFX extends Application {

    public static DSLContext context;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DemoFX.class.getResource("layouts/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        LoginController loginController = fxmlLoader.getController();
        loginController.setStage(stage);
        stage.setScene(scene);
        stage.setTitle("DoctorLight");
        stage.show();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            DataBaseConnection.Connect();
            context = DSL.using(con, SQLDialect.MYSQL);
        }).start();
        launch();
    }
}