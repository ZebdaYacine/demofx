package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController implements Initializable {

    public static ArrayList<MFXButton> btnList= new ArrayList<>();
    @FXML
    private Label username;

    @FXML
    private VBox pane;
    @FXML
    private MFXButton btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    private final int dashboardIndex=1;




    public void showUser(String data) {
        //username.setText(data);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        btnList.add(btn4);
        btnList.add(btn5);
        btnList.add(btn6);
        btnList.add(btn7);
        changePane(btnList,1-dashboardIndex, "statistics");
        btn1.setOnAction(event -> {
            changePane(btnList,1-dashboardIndex, "statistics");
        });
        btn2.setOnAction(event -> {
            changePane(btnList,2-dashboardIndex, "users");
        });
    }

    private void changePane(ArrayList<MFXButton> btnList,int index,String layout){
        btnList.forEach(mfxButton -> {
            mfxButton.setStyle("-fx-background-color : transparent;");
        });
        btnList.get(index).setStyle("-fx-background-color : linear-gradient( #b6b4fc 80%, #b2ccdc 100%);");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DemoFX.class.getResource("/com/example/demofx/layouts/"+layout+".fxml"));
            Parent root = fxmlLoader.load();
            pane.getChildren().removeAll(pane.getChildren());
            if (root != null) {
                pane.getChildren().add(root);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





}