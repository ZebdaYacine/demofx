/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.DiagnosticRecord;
import com.example.demofx.model.*;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.DIAGNOSTIC;

/**
 * FXML Controller class
 *
 * @author harran
 */
public class DiagnosticController implements Initializable {

    @FXML
    private  MFXPaginatedTableView<DiagnosticModel> table;
    @FXML
    private MFXFilterComboBox<PatientModel> patientCmbox;

    @FXML
    private Label lab;

    @FXML
    private MFXFilterComboBox<ServiceModel> serviceCmbox;
    @FXML
    private MFXFilterComboBox<UserModel> doctorCmbox, psychologistCmbox;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXDatePicker dateEnter, dateDiagnostic;
    public static ObservableList<DiagnosticModel> listDiagnostic;
    public static int currentPage;

    private static Long ID;

    DialogsController dialogsController;


    static DiagnosticModel diagnosticModel;
    static UtilsModel utilsModel;

    private void loadDataToLayout() {
        diagnosticModel = new DiagnosticModel();
        serviceCmbox.setItems(UserModel.getAllServices());
        patientCmbox.getItems().addAll(PatientModel.fetchPatients());
        doctorCmbox.setItems(UserModel.fetchUser(UtilsModel.TypeUser.doctor.toString()));
        psychologistCmbox.setItems(UserModel.fetchUser(UtilsModel.TypeUser.psychologist.toString()));
        table.autosizeColumnsOnInitialization();
        setupTable();
    }

    private void fillInputs(DiagnosticModel diagnosticModel) {
        ID = Long.parseLong(diagnosticModel.getId().toString());
        if (diagnosticModel.getDatediagnostic() != null) {
            dateDiagnostic.setValue(diagnosticModel.getDatediagnostic());
        }
        patientCmbox.getSelectionModel().selectItem(PatientModel.getPatientById(diagnosticModel.getIdpatient()));
        doctorCmbox.getSelectionModel().selectItem(UserModel.getUser(diagnosticModel.getIddoctor(), 1L));
        psychologistCmbox.getSelectionModel().selectItem(UserModel.getUser(diagnosticModel.getIdpsychologist(), 2L));
        serviceCmbox.getSelectionModel().selectItem(ServiceModel.getServiceByFollowId(diagnosticModel.getIdfollow()));
        dateEnter.setValue(FollowModel.getDateEnterByFollowId(diagnosticModel.getIdfollow()).getDateenter());
    }

    private void changeStyl(Boolean ok) {
        if (ok == true) {
            lab.setText("الإطلاع على التشخيصات");
            lab.setTextFill(Color.GREEN);
        } else {
            lab.setText("إضافة التشخيصات");
            lab.setTextFill(Color.RED);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataToLayout();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            DiagnosticModel tabDiag = table.getSelectionModel().getSelectedValue();
            if (tabDiag != null) {
                diagnosticModel=tabDiag;
                boolean ok = tabDiag.getConclusion() != null && tabDiag.getPsychologydiagnostic() != null
                        && tabDiag.getMedicladiagnostic() != null && tabDiag.getInterviewdynamics() != null;
                changeStyl(ok);
                fillInputs(tabDiag);
            }
        });
        lab.setOnMouseClicked(event -> {
            showDiagnostics("diagnostic2",diagnosticModel);
        });
        add.setOnAction(event -> {
            DiagnosticRecord diagnosticRecord = initRecord();
            if (diagnosticRecord != null) {
                try {
                    diagnosticRecord.store();
                } catch (Exception e) {
                    System.out.println("diagnostic was not inserted..!!");
                }
            }
            refrechLayout();
            //dialogsController.openInfo("تم عملية الإضافة بنجاح");
        });
        delete.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            context.delete(DIAGNOSTIC).where(DIAGNOSTIC.ID.eq(ID)).execute();
            refrechLayout();
        });
        update.setOnAction(event -> {
            try {
                DiagnosticRecord diagnosticRecord = initRecord();
                if (diagnosticRecord != null) {
                    diagnosticRecord.setId(ID);
                    diagnosticRecord.update();
                    refrechLayout();
                    //dialogsController.openInfo("تم عملية التعديل بنجاح");
                } else {
                    //Utils.trackingException(null,"حدث خطأ في عملية الإضافة",dialogsController);
                }
            } catch (Exception e) {
                //Utils.trackingException(e,"حدث خطأ في عملية الإضافة",dialogsController);
            }
        });
    }

    private void refrechLayout() {
        listDiagnostic = FXCollections.observableArrayList(diagnosticModel.getAllDiagnostic());
        table.setItems(listDiagnostic);
        table.goToPage(currentPage);
        table.setCurrentPage(currentPage);
        clearInputs();
    }

    private void clearInputs() {
        dateEnter.setText("");
        ID = 0L;
    }

    private FollowModel initFollow() {
        FollowModel followModel = new FollowModel();
        followModel.setIdservice(serviceCmbox.getSelectedItem().getId());
        followModel.setDateenter(dateEnter.getValue());
        followModel.setIdpatient(patientCmbox.getSelectedItem().getId());
        System.out.println(followModel.getIdservice() + " " + followModel.getDateenter() + " " + dateEnter.getValue() + " " + followModel.getIdpatient());
        try {
            long idFollow = FollowModel.getFollowId(followModel).getId();
            //TODO CHECK IF ID FOLLOW IS NOT NULL
            followModel.setId(idFollow);
        } catch (Exception e) {
            System.err.println("FOLLOW IS NULL");
            followModel.setId(0L);
            //e.getStackTrace();
        }
        return followModel;
    }

    private DiagnosticRecord initRecord() {
        currentPage = table.getCurrentPage();
        long idFollow = initFollow().getId();
        DiagnosticRecord diagnosticRecord = DemoFX.context.newRecord(DIAGNOSTIC);
        if (idFollow != 0L) {
            diagnosticRecord.setIdpsychologist(psychologistCmbox.getSelectedItem().getId());
            diagnosticRecord.setDatediagnostic(dateDiagnostic.getValue());
            diagnosticRecord.setIdpatient(patientCmbox.getSelectedItem().getId());
            diagnosticRecord.setIdfollow(idFollow);
            diagnosticRecord.setIddoctor(doctorCmbox.getSelectedItem().getId());
            return diagnosticRecord;
        } else {
            return null;
        }
    }

    private void setupTable() {
        MFXTableColumn<DiagnosticModel> IdColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(DiagnosticModel::getId));
        MFXTableColumn<DiagnosticModel> FollowColumn = new MFXTableColumn<>(" رقم الـمتابعة الطبية", true, Comparator.comparing(DiagnosticModel::getIdfollow));
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

        table.getTableColumns().addAll(IdColumn, FollowColumn, PatientColumn, DrColumn, PsColumn, dateDiagnosticColumn);
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

    private void showDiagnostics(String layout,DiagnosticModel diagnosticModel) {
        if(diagnosticModel.getId()!=null){
            try {
                FXMLLoader main = new FXMLLoader(getClass().getResource("/com/example/demofx/layouts/" + layout + ".fxml"));
                Parent root = main.load();
                DiagnosticController1 diagnosticController1 = main.getController();
                diagnosticController1.fillInputs(diagnosticModel,table,lab);
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


}
