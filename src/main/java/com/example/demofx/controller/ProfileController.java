package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import com.example.demofx.model.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demofx.controller.LoginController.main;
import static com.example.demofx.controller.LoginController.userLoggIn;
import static com.example.demofx.databaseManger.jooq.Tables.USER;

public class ProfileController implements Initializable {

    @FXML
    private MFXTextField username;
    @FXML
    private MFXTextField password,password1,passwordOld;

    @FXML
    private MFXButton edite;


    private UserRecord initRecord(){
        UserRecord userModel = DemoFX.context.newRecord(USER);
        userModel.setUsername(username.getText());
        userModel.setPassword(password.getText());
        return userModel ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        edite.setOnAction(event -> {
            if(username.getText()!=null&&passwordOld.getText()!=null&& password.getText().equals(password1.getText())){
                try{
                    if(userLoggIn.getPassword().equals(passwordOld.getText())){
                        if(UserModel.getUserLoggIn(username.getText(),password.getText()).getId()==null){
                            UserRecord userRecord=initRecord();
                            userRecord.setId(userLoggIn.getId());
                            userRecord.update();
                            new Alert(Alert.AlertType.INFORMATION, "تم  إتمام العملية").show();
                            userLoggIn.setPassword(password.getText());
                            userLoggIn.setUsername(username.getText());
                            MainController mainController= main.getController();
                            mainController.showUser(userLoggIn);
                        }else {
                            new Alert(Alert.AlertType.ERROR, "خطأ في  إتمام العملية  اسم المستخدم محجوز").show();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "كلمة السر القديمة خاطئة").show();
                    }
                }catch (Exception e){
                    new Alert(Alert.AlertType.ERROR, "خطأ في  إتمام العملية  حاول مرة اخرى").show();
                }
            }else{
                if(username.getText()==null){
                    new Alert(Alert.AlertType.ERROR, "إسم المستخدم فارغ").show();
                }else if(passwordOld.getText()==null){
                    new Alert(Alert.AlertType.ERROR, "كلمة السر غير متطابقة").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "كلمة السر القديمة فارغة").show();
                }
            }
        });
    }
}