package com.example.demofx.controller;

import com.example.demofx.model.DiagnosticModel;
import com.example.demofx.model.FollowModel;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class FolderMidecalController implements Initializable {
    @FXML
    private MFXPaginatedTableView<FollowModel> tableFollow;
    @FXML
    private MFXPaginatedTableView<DiagnosticModel> tableDiagnostic;
    public static ObservableList<FollowModel> listFollow;
    public static ObservableList<DiagnosticModel> listDiagnostic;
    public static int currentPage1;
    public static int currentPage2;
    private static Long ID;
    static FollowModel followModel ;
    static DiagnosticModel diagnosticModel ;

    private void clearInputs() {
        ID = 0L;
    }

    private void initLayout(){
        followModel= new FollowModel();
        diagnosticModel=new DiagnosticModel();
        currentPage1=tableFollow.getCurrentPage();
        currentPage2=tableDiagnostic.getCurrentPage();
        tableFollow.autosizeColumnsOnInitialization();
        tableDiagnostic.autosizeColumnsOnInitialization();
        setupTableFollow();
        setupTableDiagnostics(0);
        ID=0L;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLayout();
        tableFollow.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            FollowModel followModel = tableFollow.getSelectionModel().getSelectedValue();
            if (followModel != null) {
                listDiagnostic = FXCollections.observableArrayList(diagnosticModel.getAllDiagnosticByFollowID(followModel.getId()));
                tableDiagnostic.setItems(listDiagnostic);
                tableDiagnostic.goToPage(0);
                tableDiagnostic.setCurrentPage(0);
            }
        });

        tableDiagnostic.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            DiagnosticModel diagnosticModel = tableDiagnostic.getSelectionModel().getSelectedValue();
            if (diagnosticModel != null) {
                showDiagnostics("diagnosticDetailsPrint",diagnosticModel);
            }
        });
    }

    private void setupTableDiagnostics(long idFollow) {
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

        tableDiagnostic.getTableColumns().addAll(IdColumn, FollowColumn, PatientColumn, DrColumn, PsColumn, dateDiagnosticColumn);
        tableDiagnostic.getFilters().addAll(
                new LongFilter<>("ID", DiagnosticModel::getId),
                new LongFilter<>("رقم الـمتابعة الطبية", DiagnosticModel::getIdfollow),
                new StringFilter<>("الـمريض", DiagnosticModel::getPatientFullName),
                new StringFilter<>("مسؤول التشخيص الطبي", DiagnosticModel::getDrFullName),
                new StringFilter<>("مسؤول التشخيص النفسي", DiagnosticModel::getPsFullName),
                new StringFilter<>("تاريخ التشخيص", DiagnosticModel::getDateDiagnosticToString)
        );
        listDiagnostic = FXCollections.observableArrayList(diagnosticModel.getAllDiagnosticByFollowID(idFollow));
        tableDiagnostic.setItems(listDiagnostic);
        tableDiagnostic.goToPage(0);
        tableDiagnostic.setCurrentPage(0);
    }

    private void setupTableFollow() {
        MFXTableColumn<FollowModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(FollowModel::getId));
        MFXTableColumn<FollowModel> PatientColumn = new MFXTableColumn<>(" المريض", true, Comparator.comparing(FollowModel::getPatientFullName));
        MFXTableColumn<FollowModel> SicknessColumn = new MFXTableColumn<>("المرض", true, Comparator.comparing(FollowModel::getSickness));
        MFXTableColumn<FollowModel> StatusColumn = new MFXTableColumn<>("الحالة", true, Comparator.comparing(FollowModel::getStatus));
        MFXTableColumn<FollowModel> ServiceColumn = new MFXTableColumn<>("المصلحة", true, Comparator.comparing(FollowModel::getServiceName));
        MFXTableColumn<FollowModel> dateEnterColumn = new MFXTableColumn<>("تاريخ الدخول ", true, Comparator.comparing(FollowModel::getDateenterToString));
        MFXTableColumn<FollowModel> dateLeaveColumn = new MFXTableColumn<>("تاريخ الخروج ", true, Comparator.comparing(FollowModel::getDategoToString));
        MFXTableColumn<FollowModel> AddressColumn = new MFXTableColumn<>("العنوان", true, Comparator.comparing(FollowModel::getPatientAddress));
        MFXTableColumn<FollowModel> dateColumn = new MFXTableColumn<>("تاريخ الميلاد", true, Comparator.comparing(FollowModel::getBirthdayDate));

        Idcolumn.setRowCellFactory(followModel -> new MFXTableRowCell<>(FollowModel::getId));
        PatientColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getPatientFullName));
        SicknessColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getSickness));
        StatusColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getStatus));
        AddressColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getPatientAddress));
        ServiceColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getServiceName));
        dateEnterColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getDateenterToString));
        dateLeaveColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getDategoToString));
        dateColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getBirthdayDate));

        tableFollow.getTableColumns().addAll(Idcolumn, PatientColumn,dateColumn,AddressColumn, SicknessColumn, StatusColumn, ServiceColumn, dateEnterColumn, dateLeaveColumn);
        tableFollow.getFilters().addAll(
                new LongFilter<>("ID", FollowModel::getId),
                new StringFilter<>("المريض", FollowModel::getPatientFullName),
                new StringFilter<>("الحالة", FollowModel::getStatus),
                new StringFilter<>("تاريخ الدخول", FollowModel::getDateenterToString),
                new StringFilter<>("تاريخ الخروج", FollowModel::getDategoToString),
                new StringFilter<>("العنوان", FollowModel::getBirthdayDate),
                new StringFilter<>("تاريخ الـميلاد", FollowModel::getPatientAddress),
                new StringFilter<>("الـمصلحة", FollowModel::getServiceName),
                new StringFilter<>("المرض", FollowModel::getSickness)
                );
        listFollow = FXCollections.observableArrayList(followModel.getAllFollows());
        tableFollow.setItems(listFollow);
        tableFollow.goToPage(0);
        tableFollow.setCurrentPage(0);
    }

    private void showDiagnostics(String layout, DiagnosticModel diagnosticModel) {
        if (diagnosticModel.getId() != null) {
            try {
                FXMLLoader main = new FXMLLoader(getClass().getResource("/com/example/demofx/layouts/" + layout + ".fxml"));
                Parent root = main.load();
                DiagnosticDetailsPrintController diagnosticDetailsPrintController = main.getController();
                diagnosticDetailsPrintController.fillInputs(diagnosticModel, tableDiagnostic);
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