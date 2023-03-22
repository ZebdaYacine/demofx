package com.example.demofx.controller;

import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jooq.Record;
import org.jooq.Result;

import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.tables.User.USER;

public class LoginController {

    @FXML
    private MFXTextField username;
    @FXML
    private MFXTextField password;

    @FXML
    private MFXButton btn_logn;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    private void getAll() {
        Result<Record> result = context.select().from(USER).fetch();
        String n = " ";
        for (Record r : result) {
            long id = r.getValue(USER.ID);
            String firstname = r.getValue(USER.FIRSTNAME);
            n = n + id + "  " + firstname + "\n";
        }
    }

    @FXML
    protected void add(ActionEvent event) {
       /* UserRecord userRecord = context.newRecord(USER);
        userRecord.setFirstname(username.getText());
        userRecord.setLastname(password.getText());
        userRecord.setIdrole(1L);
        userRecord.setIdservice(1L);
        userRecord.setIdtype(1L);
        userRecord.store();
        getAll();*/

        try {
            Stage thisStage = (Stage) btn_logn.getScene().getWindow();
            thisStage.close();
            FXMLLoader main = new FXMLLoader(getClass().getResource("/com/example/demofx/layouts/main.fxml"));
            Parent root=main.load();
            MainController mainController=main.getController();
            mainController.showUser(username.getText());
            /*if (screenSize.getWidth() <= 1366) {
                screenSize.width = 1340;
                screenSize.height = 900;
            } else {
                screenSize.width = 1570;
                screenSize.height = 900;
            }*/
            Scene home_scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(home_scene);
            stage.setTitle("Doctor Light");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void delete() {
        Random rn = new Random();
        long id = rn.nextLong(30) + 1;
        UserRecord userRecord = context.fetchOne(USER, USER.ID.eq(id));
        if (userRecord != null) {
            userRecord.delete();
            getAll();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No record").show();
        }

    }

    @FXML
    protected void update() {
        Random rn = new Random();
        long id = rn.nextLong(30) + 1;
        UserRecord userRecord = context.fetchOne(USER, USER.ID.eq(id));
        if (userRecord != null) {
            context.update(USER).set(USER.FIRSTNAME, "HARRAN").set(USER.LASTNAME, "KADRI").where(USER.ID.eq(id)).execute();
            getAll();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No record").show();
        }
    }
}