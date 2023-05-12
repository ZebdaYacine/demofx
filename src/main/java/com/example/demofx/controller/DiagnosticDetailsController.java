/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.DiagnosticRecord;
import com.example.demofx.model.DiagnosticModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demofx.databaseManger.jooq.Tables.DIAGNOSTIC;

/**
 * FXML Controller class
 *
 * @author harran
 */
public class DiagnosticDetailsController implements Initializable {

    @FXML
    private MFXButton save;

    @FXML
    static MFXPaginatedTableView<DiagnosticModel> tableCurrent;
    @FXML
    private TextArea textM,textP,textI,textC;

    @FXML
    private Label labCurrent,lab1,lab2,lab3,lab4,update1,update2,update3,update4;


    private static DiagnosticModel diagnosticModelCurrent;


    private void loadDataToLayout() {
        textM.setText(diagnosticModelCurrent.getMedicladiagnostic());
        textP.setText(diagnosticModelCurrent.getPsychologydiagnostic());
        textC.setText(diagnosticModelCurrent.getConclusion());
        textI.setText(diagnosticModelCurrent.getInterviewdynamics());
        lab1.setText(textM.getLength()+"/500");
        lab2.setText(textP.getLength()+"/500");
        lab3.setText(textI.getLength()+"/500");
        lab4.setText(textC.getLength()+"/500");
    }
    public void fillInputs(DiagnosticModel diagnosticModel,MFXPaginatedTableView<DiagnosticModel> table,Label lab) {
        diagnosticModelCurrent=diagnosticModel;
        tableCurrent=table;
        loadDataToLayout();
        labCurrent=lab;
    }

    private void checkTextAreaLength(TextArea text,Label lab){
        text.setOnKeyReleased(event -> {
            int nbr =text.getLength();
            if(nbr <500){
                text.setEditable(true);
                lab.setText(nbr+"/500");
            }else{
                lab.setText("500/500");
                text.setStyle("-fx-border-color: #8a0b0b; -fx-border-width: 1");
                text.setEditable(false);
            }
        });
    }

    private void updateTextArea(Label text,TextArea textArea){
        text.setOnMouseClicked(event -> {
            textArea.setEditable(true);
            textArea.setStyle("-fx-border-color: #fff; -fx-border-width: 1");
        });
    }

    private DiagnosticRecord initRecord() {
        DiagnosticRecord diagnosticRecord = DemoFX.context.newRecord(DIAGNOSTIC);
        if (diagnosticModelCurrent.getId() != 0L) {
            diagnosticRecord.setMedicladiagnostic(textM.getText());
            diagnosticRecord.setPsychologydiagnostic(textP.getText());
            diagnosticRecord.setConclusion(textC.getText());
            diagnosticRecord.setInterviewdynamics(textI.getText());
            diagnosticRecord.setId(diagnosticModelCurrent.getId());
            return diagnosticRecord;
        } else {
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkTextAreaLength(textM,lab1);
        checkTextAreaLength(textP,lab2);
        checkTextAreaLength(textI,lab3);
        checkTextAreaLength(textC,lab4);
        updateTextArea(update1,textM);
        updateTextArea(update2,textP);
        updateTextArea(update3,textI);
        updateTextArea(update4,textC);
        save.setOnAction(event -> {
            DiagnosticRecord diagnosticRecord = initRecord();
            if (diagnosticRecord != null) {
                try {
                    diagnosticRecord.update();
                    tableCurrent.setItems(FXCollections.observableArrayList(new DiagnosticModel().getAllDiagnostic()));
                    tableCurrent.goToPage(0);
                    tableCurrent.setCurrentPage(0);
                    boolean ok = Utils.checkStringIsValid(diagnosticRecord.getConclusion()) &&
                            Utils.checkStringIsValid(diagnosticRecord.getPsychologydiagnostic()) &&
                            Utils.checkStringIsValid(diagnosticRecord.getMedicladiagnostic()) &&
                            Utils.checkStringIsValid(diagnosticRecord.getInterviewdynamics());
                    if (ok == true) {
                        labCurrent.setText("الإطلاع على التشخيصات");
                        labCurrent.setTextFill(Color.GREEN);
                    } else {
                        labCurrent.setText("إضافة التشخيصات");
                        labCurrent.setTextFill(Color.RED);

                    }
                } catch (Exception e) {
                    System.out.println("diagnostic was not inserted..!!");
                }
            }
        });
    }







}
