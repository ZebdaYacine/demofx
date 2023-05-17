package com.example.demofx.controller;

import com.example.demofx.model.PatientModel;
import com.example.demofx.model.ServiceModel;
import com.example.demofx.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {

    @FXML
    private Text text1,text2,text3,text4;

    @FXML
    private Hyperlink link1,link2,link3,link4;

    public static boolean isVisited=false;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int a=ServiceModel.getAllServices().size();
        int b=PatientModel.getAllPatients().size();
        int c=UserModel.getAllUserByType("doctor").size();
        int d=UserModel.getAllUserByType("psychologist").size();
        if(!isVisited){
            loadData(a,text1);
            loadData(b,text2);
            loadData(c,text3);
            loadData(d,text4);
            isVisited=true;
        }else{
            text1.setText(a+"");
            text2.setText(b+"");
            text3.setText(c+"");
            text4.setText(d+"");
        }
        link1.setOnAction(event -> {
            showList("servicesList");
        });

        link2.setOnAction(event -> {
            showList("patienstList");
        });
        link3.setOnAction(event -> {
            showList("usersList");
        });
        link4.setOnAction(event -> {
            showList("psychologistesList");
        });

    }

    private void loadData(int a , Text text){
        new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while(i<a){
                    i=i+1;
                    try {
                        Thread.sleep(50);
                    }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String str=i+"";
                    initText(str,text);
                }
            }
        }).start();
    }


    private void initText(String  str , Text text){
        new Thread(new Runnable() {
            @Override
            public void run() {
                text.setText(str);
            }
        }).start();
    }


    private void showList(String layout) {
            try {
                FXMLLoader main = new FXMLLoader(getClass().getResource("/com/example/demofx/layouts/" + layout + ".fxml"));
                Parent root = main.load();
                Scene home_scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(home_scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }




}