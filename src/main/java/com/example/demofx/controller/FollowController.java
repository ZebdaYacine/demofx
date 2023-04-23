package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.FollowRecord;
import com.example.demofx.model.*;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.FOLLOW;

public class FollowController implements Initializable {
    @FXML
    private MFXPaginatedTableView<FollowModel> table;
    @FXML
    private MFXTextField  sickness, status;
    @FXML
    private MFXFilterComboBox<PatientModel> patientCmbox;
    @FXML
    private MFXFilterComboBox<ServiceModel> serviceCmbox;
    @FXML
    private MFXFilterComboBox<UserModel>   doctorCmbox, psychologistCmbox;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXDatePicker dateEnter, dateGo;

    public static ObservableList<FollowModel> listFollow;

    public static int currentPage;

    DialogsController dialogsController;

    private ArrayList<String> genderList;
    private ArrayList<String> civilStatusList;
    private static Long ID;

    public static SimpleStringProperty dateEnterProperty,dateGoProperty,sicknessProperty,statusProperty;

    static FollowModel followModel ;
    static UtilsModel utilsModel;




    private void initDataBinding(){
        sicknessProperty=new SimpleStringProperty();
        statusProperty=new SimpleStringProperty();
        dateEnterProperty=new SimpleStringProperty();
        dateGoProperty=new SimpleStringProperty();
        statusProperty=new SimpleStringProperty();
        statusProperty.bindBidirectional(status.textProperty());
        sicknessProperty.bindBidirectional(sickness.textProperty());
        dateEnterProperty.bindBidirectional(dateEnter.textProperty());
        dateGoProperty.bindBidirectional(dateGo.textProperty());
    }

    private void fillInputs(FollowModel followRecord) {
        ID = Long.parseLong(followRecord.getId().toString());
        if(followRecord.getSickness()!= null) {
            sicknessProperty.set(followRecord.getSickness());
        }
        if(followRecord.getStatus()!= null){
            statusProperty.set(followRecord.getStatus());
        }
        if(followRecord.getDatego()!= null)
             dateGoProperty.set(followRecord.getDatego().toString());
        if(followRecord.getDateenter()!= null)
            dateEnterProperty.set(followRecord.getDateenter().toString());
    }

    private void clearInputs() {
        sicknessProperty.set("");
        statusProperty.set("");
        dateGoProperty.set("");
        dateEnterProperty.set("");
        ID = 0L;
    }

    private void loadDataToLayout() {
        utilsModel= new UtilsModel();
        genderList = new ArrayList<>();
        civilStatusList = new ArrayList<>();
        civilStatusList.add("اغزب");
        civilStatusList.add("متزوج");
        civilStatusList.add("أرمل");
        civilStatusList.add("مطلق");
        genderList.add("ذكر");
        genderList.add("أنثى");
        setupTable();
        patientCmbox.getItems().addAll(PatientModel.fetchPatients());
        doctorCmbox.setItems(UserModel.fetchUser(UtilsModel.TypeUser.doctor.toString()));
        psychologistCmbox.setItems(UserModel.fetchUser(UtilsModel.TypeUser.psychologist.toString()));
        serviceCmbox.setItems(UserModel.getAllServices());
        table.autosizeColumnsOnInitialization();
    }

    private FollowRecord initRecord() {
        currentPage = table.getCurrentPage();
        FollowRecord followRecord = DemoFX.context.newRecord(FOLLOW);
        followRecord.setSickness(sicknessProperty.getValue());
        followRecord.setStatus(statusProperty.getValue());
        followRecord.setIdpatient(patientCmbox.getSelectionModel().getSelectedItem().getId());
        followRecord.setIddoctor(doctorCmbox.getSelectionModel().getSelectedItem().getId());
        followRecord.setIdpsychologist(psychologistCmbox.getSelectionModel().getSelectedItem().getId());
        followRecord.setIdservice(serviceCmbox.getSelectionModel().getSelectedItem().getId());
        followRecord.setDateenter(dateEnter.getValue());
        followRecord.setDatego(dateGo.getValue());
        return followRecord;
    }

    private void refrechLayout() {
        listFollow = FXCollections.observableArrayList(followModel.getAllFollows());
        table.setItems(listFollow);
        table.goToPage(currentPage);
        table.setCurrentPage(currentPage);
        clearInputs();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        followModel=new FollowModel();
        dialogsController = new DialogsController();
        initDataBinding();
        loadDataToLayout();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            FollowModel followModel = table.getSelectionModel().getSelectedValue();
            if (followModel != null) {
                fillInputs(followModel);
            }
        });
        add.setOnAction(event -> {
            try {
                initRecord().store();
                refrechLayout();
                dialogsController.openInfo("تم عملية الإضافة بنجاح");
            } catch (Exception e) {
                Utils.trackingException(e,"حدث خطأ في عملية الإضافة",dialogsController);
            }
        });
        delete.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            context.delete(FOLLOW).where(FOLLOW.ID.eq(ID)).execute();
            refrechLayout();
        });
        update.setOnAction(actionEvent -> {
            try {
                FollowRecord followRecord = initRecord();
                followRecord.setId(ID);
                followRecord.update();
                refrechLayout();
                dialogsController.openInfo("تم عملية التعديل بنجاح");
            } catch (Exception e) {
                Utils.trackingException(e,"حدث خطأ في عملية الإضافة",dialogsController);
            }
        });
    }

    private void setupTable() {
        MFXTableColumn<FollowModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(FollowModel::getId));
        MFXTableColumn<FollowModel> PatientColumn = new MFXTableColumn<>(" المريض", true, Comparator.comparing(FollowModel::getPatientFullName));
        MFXTableColumn<FollowModel> SicknessColumn = new MFXTableColumn<>("المرض", true, Comparator.comparing(FollowModel::getSickness));
        MFXTableColumn<FollowModel> DrColumn = new MFXTableColumn<>("الطبيب", true, Comparator.comparing(FollowModel::getDrFullName));
        MFXTableColumn<FollowModel> Pscolumn = new MFXTableColumn<>("النفساني", true, Comparator.comparing(FollowModel::getPsFullName));
        MFXTableColumn<FollowModel> StatusColumn = new MFXTableColumn<>("الحالة", true, Comparator.comparing(FollowModel::getStatus));
        MFXTableColumn<FollowModel> ServiceColumn = new MFXTableColumn<>("المصلحة", true, Comparator.comparing(FollowModel::getServiceName));
        MFXTableColumn<FollowModel> dateEnterColumn = new MFXTableColumn<>("تاريخ الدخول ", true, Comparator.comparing(FollowModel::getDateenterToString));
        MFXTableColumn<FollowModel> dateLeaveColumn = new MFXTableColumn<>("تاريخ الخروج ", true, Comparator.comparing(FollowModel::getDategoToString));

        Idcolumn.setRowCellFactory(followModel -> new MFXTableRowCell<>(FollowModel::getId));
        DrColumn.setRowCellFactory(followModel -> new MFXTableRowCell<>(FollowModel::getDrFullName));
        PatientColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getPatientFullName));
        SicknessColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getSickness));
        Pscolumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getPsFullName));
        StatusColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getStatus));
        ServiceColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getServiceName));
        dateEnterColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getDateenterToString));
        dateLeaveColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(FollowModel::getDategoToString));

        table.getTableColumns().addAll(Idcolumn, PatientColumn, SicknessColumn, DrColumn, Pscolumn, StatusColumn, ServiceColumn, dateEnterColumn, dateLeaveColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", FollowModel::getId),
                new StringFilter<>("المريض", FollowModel::getPatientFullName),
                new StringFilter<>("الطبيب", FollowModel::getDrFullName),
                new StringFilter<>("النفساني", FollowModel::getPsFullName),
                new StringFilter<>("المرض", FollowModel::getSickness),
                new StringFilter<>("الحالة", FollowModel::getStatus),
                new StringFilter<>("تاريخ الدخول", FollowModel::getDateenterToString),
                new StringFilter<>("تاريخ الخروج", FollowModel::getDategoToString),
                new StringFilter<>("المرض", FollowModel::getSickness)
                );
        listFollow = FXCollections.observableArrayList(followModel.getAllFollows());
        table.setItems(listFollow);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}