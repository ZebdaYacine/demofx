package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.model.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController implements Initializable {

    public static ArrayList<MFXButton> btnList= new ArrayList<>();
    @FXML
    private Label username,Lname,Fname;

    @FXML
    private ImageView profile,close;

    @FXML
    private VBox pane;
    @FXML
    private MFXButton btn1;
    @FXML
    private MFXButton btn2,btn3,btn4,btn5,btn6;
    private final int dashboardIndex=1;


    public Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void showUser(UserModel data) {
        username.setText("إسم الـمستخدم :"+data.getUsername());
        Fname.setText("الإسم :"+data.getFirstname());
        Lname.setText("اللقب :"+data.getLastname());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* if(LoginController.userLoggIn.getRole().equals("user")){
            btn6.setVisible(false);
        }*/
        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        btnList.add(btn4);
        btnList.add(btn5);
        btnList.add(btn6);

        close.setOnMouseClicked(event -> {
            Stage thisStage = (Stage) close.getScene().getWindow();
            thisStage.close();
        });

        profile.setOnMouseClicked(event -> {
            try {
                FXMLLoader main = new FXMLLoader(getClass().getResource("/com/example/demofx/layouts/profile.fxml"));
                Parent root = main.load();
                Scene home_scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(home_scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        changePane(btnList,1-dashboardIndex, "statistics",pane);
        btn1.setOnAction(event -> {
            changePane(btnList,1-dashboardIndex, "statistics",pane);
        });
        btn2.setOnAction(event -> {
            changePane(btnList,2-dashboardIndex, "users",pane);
        });
        btn3.setOnAction(event -> {
            changePane(btnList,3-dashboardIndex, "patient",pane);
        });
        btn4.setOnAction(event -> {
            changePane(btnList,4-dashboardIndex, "follow",pane);
        });
        btn5.setOnAction(event -> {
            changePane(btnList,5-dashboardIndex, "diagnostic1",pane);
        });
        btn6.setOnAction(event -> {
            changePane(btnList,5-dashboardIndex, "folderMidecal",pane);
        });
    }

    private void changePane(ArrayList<MFXButton> btnList,int index,String layout ,VBox pane){
            btnList.forEach(mfxButton -> {
                mfxButton.setStyle("-fx-background-color : transparent;");
            });
            btnList.get(index).setStyle("-fx-background-color : #691cc5;");
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