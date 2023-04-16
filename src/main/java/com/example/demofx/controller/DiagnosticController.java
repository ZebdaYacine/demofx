/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demofx.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import com.example.demofx.model.FollowModel;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.Record;
import org.jooq.Result;
import java.util.ArrayList;
import java.util.Comparator;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.FOLLOW;
import static com.example.demofx.databaseManger.jooq.Tables.PATIENT;

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
