/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demofx.controller;

import com.example.demofx.model.DiagnosticModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author harran
 */
public class DiagnosticDetailsPrintController implements Initializable {
    @FXML
    private MFXButton print;
    @FXML
    static MFXPaginatedTableView<DiagnosticModel> tableCurrent;
    @FXML
    private TextArea textM,textP,textI,textC;
    private static DiagnosticModel diagnosticModelCurrent;
    private void loadDataToLayout() {
        textM.setText(diagnosticModelCurrent.getMedicladiagnostic());
        textP.setText(diagnosticModelCurrent.getPsychologydiagnostic());
        textC.setText(diagnosticModelCurrent.getConclusion());
        textI.setText(diagnosticModelCurrent.getInterviewdynamics());
    }
    public void fillInputs(DiagnosticModel diagnosticModel,MFXPaginatedTableView<DiagnosticModel> table) {
        diagnosticModelCurrent=diagnosticModel;
        tableCurrent=table;
        loadDataToLayout();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        print.setOnAction(event -> {

        });
    }
}
