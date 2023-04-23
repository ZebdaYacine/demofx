/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demofx.controller;

import com.example.demofx.model.*;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author harran
 */
public class DiagnosticController implements Initializable {

    @FXML
    private MFXPaginatedTableView<DiagnosticModel> table;
    @FXML
    private MFXFilterComboBox<PatientModel> patientCmbox;
    @FXML
    private MFXFilterComboBox<FollowModel> followCmbox;
    @FXML
    private MFXFilterComboBox<UserModel>   doctorCmbox, psychologistCmbox;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXDatePicker dateDiagnostic;
    public static ObservableList<DiagnosticModel> listDiagnostic;
    public static int currentPage;

    private static Long ID;


    static DiagnosticModel diagnosticModel ;
    static UtilsModel utilsModel;

    private void loadDataToLayout() {
        diagnosticModel= new DiagnosticModel();
        setupTable();
        followCmbox.getItems().addAll(FollowModel.fetchFollow());
        patientCmbox.getItems().addAll(PatientModel.fetchPatients());
        doctorCmbox.setItems(UserModel.fetchUser(UtilsModel.TypeUser.doctor.toString()));
        psychologistCmbox.setItems(UserModel.fetchUser(UtilsModel.TypeUser.psychologist.toString()));
        table.autosizeColumnsOnInitialization();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataToLayout();
        followCmbox.setOnAction((e) -> {
            long id=followCmbox.getSelectionModel().getSelectedItem().getId();
            patientCmbox.getItems().addAll(DiagnosticModel.fetchPatients(id));
        });
    }

    private void setupTable() {
        MFXTableColumn<DiagnosticModel> IdColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(DiagnosticModel::getId));
        MFXTableColumn<DiagnosticModel> FollowColumn = new MFXTableColumn<>(" رقم الـمتابعة الطبية", true, Comparator.comparing(DiagnosticModel::getPatientFullName));
        MFXTableColumn<DiagnosticModel> PatientColumn = new MFXTableColumn<>(" المريض", true, Comparator.comparing(DiagnosticModel::getPatientFullName));
        MFXTableColumn<DiagnosticModel> DrColumn = new MFXTableColumn<>("مسؤول التشخيص الطبي", true, Comparator.comparing(DiagnosticModel::getDrFullName));
        MFXTableColumn<DiagnosticModel> PsColumn = new MFXTableColumn<>("مسؤول التشخيص النفسي", true, Comparator.comparing(DiagnosticModel::getPsFullName));
        MFXTableColumn<DiagnosticModel> dateDiagnosticColumn = new MFXTableColumn<>("تاريخ التشخيص ", true, Comparator.comparing(DiagnosticModel::getDateDiagnosticToString));

        IdColumn.setRowCellFactory(diagnosticModel -> new MFXTableRowCell<>(DiagnosticModel::getId));
        DrColumn.setRowCellFactory(diagnosticModel -> new MFXTableRowCell<>(DiagnosticModel::getDrFullName));
        PatientColumn.setRowCellFactory(diagnosticModel -> new MFXTableRowCell<>(DiagnosticModel::getPatientFullName));
        FollowColumn.setRowCellFactory(diagnosticModel -> new MFXTableRowCell<>(DiagnosticModel::getIdfollow));
        PsColumn.setRowCellFactory(diagnosticModel -> new MFXTableRowCell<>(DiagnosticModel::getPsFullName));
        dateDiagnosticColumn.setRowCellFactory(diagnosticModel -> new MFXTableRowCell<>(DiagnosticModel::getDateDiagnosticToString));

        table.getTableColumns().addAll(IdColumn, PatientColumn, DrColumn, PsColumn,dateDiagnosticColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", DiagnosticModel::getId),
                new LongFilter<>("رقم الـمتابعة الطبية", DiagnosticModel::getIdfollow),
                new StringFilter<>("الـمريض", DiagnosticModel::getPatientFullName),
                new StringFilter<>("مسؤول التشخيص الطبي", DiagnosticModel::getDrFullName),
                new StringFilter<>("مسؤول التشخيص النفسي", DiagnosticModel::getPsFullName),
                new StringFilter<>("تاريخ التشخيص", DiagnosticModel::getDateDiagnosticToString)
        );
        listDiagnostic = FXCollections.observableArrayList(diagnosticModel.getAllDiagnostic());
        table.setItems(listDiagnostic);
        table.goToPage(0);
        table.setCurrentPage(0);
    }

    
    
}
