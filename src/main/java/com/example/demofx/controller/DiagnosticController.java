/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demofx.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.example.demofx.model.FollowModel;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author harran
 */
public class DiagnosticController implements Initializable {

    @FXML
    private MFXPaginatedTableView<?> table;
    @FXML
    private MFXDatePicker dateDiagnostic;
    @FXML
    private MFXFilterComboBox<?> patientCmbox;
    @FXML
    private MFXFilterComboBox<?> followCmbox;
    @FXML
    private MFXTextField sickness;
    @FXML
    private MFXTextField medicalDiagnostic;
    @FXML
    private MFXTextField psychologyDiagnostic;
    @FXML
    private MFXTextField ID;
    @FXML
    private MFXButton delete;
    @FXML
    private MFXButton update;
    @FXML
    private MFXButton add;
    public static ObservableList<FollowModel> listFollow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    


    
    
}
