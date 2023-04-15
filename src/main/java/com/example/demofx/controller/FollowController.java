package com.example.demofx.controller;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.jooq.Record;
import org.jooq.Result;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.FOLLOW;
import static com.example.demofx.databaseManger.jooq.Tables.PATIENT;

public class FollowController implements Initializable {
    @FXML
    private MFXPaginatedTableView<FollowModel> table;
    @FXML
    private MFXTextField patient, sickness, doctor, psychologist, status, service;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXDatePicker dateEnter, dateGo;
    @FXML
    private MFXComboBox<String> scientificLevelCmbox, socioEconomicLevelCmbox, genderCmbox, civilStatusCmbox;

    public static ObservableList<FollowModel> listFollow;

    public static int currentPage;

    DialogsController dialogsController;

    private ArrayList<String> genderList;
    private ArrayList<String> civilStatusList;
    private static Long ID;

    public static SimpleStringProperty patientProperty,dateEnterProperty,dateGoProperty,sicknessProperty,doctorProperty,psychologistProperty,statusProperty,serviceProperty;



    private ArrayList<FollowModel> getAllFollows() {
        Result<?> result = context.select().from(FOLLOW)
                .fetch();
        ArrayList<FollowModel> listUser = new ArrayList<>();
        for (Record r : result) {
            FollowModel followModel = new FollowModel();
            followModel.setId(r.getValue(FOLLOW.ID));
            followModel.setIdpatient(r.getValue(FOLLOW.IDPATIENT));
            followModel.setIddoctor(r.getValue(FOLLOW.IDDOCTOR));
            followModel.setIdservice(r.getValue(FOLLOW.IDSERVICE));
            followModel.setIdpsychologist(r.getValue(FOLLOW.IDPSYCHOLOGIST));
            followModel.setDatego(r.getValue(FOLLOW.DATEGO));
            followModel.setDateenter(r.getValue(FOLLOW.DATEENTER));
            followModel.setStatus(r.getValue(FOLLOW.STATUS));
            followModel.setSickness(r.getValue(FOLLOW.SICKNESS));
            listUser.add(followModel);
        }
        return listUser;
    }

    private void initDataBinding(){
/*
        AutoCompletePopup<String> autoCompletePopup = new AutoCompletePopup<>();
*/
        //autoCompletePopup.().addAll("JavaFX", "Java", "JavaScript", "JavaEE", "JavaSE");

        //inti Property
        patientProperty=new SimpleStringProperty();
        sicknessProperty=new SimpleStringProperty();
        doctorProperty =new SimpleStringProperty();
        psychologistProperty=new SimpleStringProperty();
        statusProperty=new SimpleStringProperty();
        serviceProperty=new SimpleStringProperty();
        dateEnterProperty=new SimpleStringProperty();
        dateGoProperty=new SimpleStringProperty();
        //binding Property with fields
        patientProperty.bindBidirectional(patient.textProperty());
        doctorProperty.bindBidirectional(doctor.textProperty());
        psychologistProperty.bindBidirectional(psychologist.textProperty());
        statusProperty.bindBidirectional(status.textProperty());
        serviceProperty.bindBidirectional(service.textProperty());
        sicknessProperty.bindBidirectional(sickness.textProperty());
        dateEnterProperty.bindBidirectional(dateEnter.textProperty());
        dateGoProperty.bindBidirectional(dateGo.textProperty());
    }


    private void fillInputs(FollowModel followRecord) {
        ID = Long.parseLong(followRecord.getId().toString());
        patientProperty.set(followRecord.getPatientFullName());
        doctorProperty.set(followRecord.getDrFullName());
        sicknessProperty.set(followRecord.getSickness());
        statusProperty.set(followRecord.getStatus());
        serviceProperty.set(followRecord.getServiceName());
        psychologistProperty.set(followRecord.getPsFullName());
        dateGoProperty.set(followRecord.getDatego().toString());
        dateEnterProperty.set(followRecord.getDateenterToString().toString());
    }

    private void clearInputes() {
        patient.setText("");
        sickness.setText("");
        psychologist.setText("");
        status.setText("");
        service.setText("");
        dateEnter.setText("");
        //genderCmbox.selectItem("");
        //civilStatusCmbox.selectItem("");
        ID = 0L;
    }

    private void loadDataToLayout() {
        genderList = new ArrayList<>();
        civilStatusList = new ArrayList<>();
        civilStatusList.add("اغزب");
        civilStatusList.add("متزوج");
        civilStatusList.add("أرمل");
        civilStatusList.add("مطلق");
        genderList.add("ذكر");
        genderList.add("أنثى");
        setupTable();
        table.autosizeColumnsOnInitialization();
        //genderCmbox.getItems().addAll(FXCollections.observableArrayList(genderList));
        //civilStatusCmbox.getItems().addAll(FXCollections.observableArrayList(civilStatusList));
    }

    private PatientRecord initRecord() {
        currentPage = table.getCurrentPage();
        PatientRecord pateintRecord = DemoFX.context.newRecord(PATIENT);
        if (patient.getText().isEmpty()) {
            patient.setStyle("-fx-border-color: #b61515");
        } else {
            pateintRecord.setFirstname(patient.getText());
            patient.setStyle("-fx-border-color: transparent");
        }
        pateintRecord.setLastname(sickness.getText());
        pateintRecord.setWieght(Double.parseDouble(service.getText()));
        pateintRecord.setHeight(Integer.parseInt(status.getText()));
        pateintRecord.setGender(genderCmbox.getSelectionModel().getSelectedItem().toString());
        pateintRecord.setCivilstatus(civilStatusCmbox.getSelectionModel().getSelectedItem().toString());
        pateintRecord.setAddress(psychologist.getText());
        pateintRecord.setBirthday(dateEnter.getValue());
        return pateintRecord;
    }

    private void refrechLayout() {
        listFollow = FXCollections.observableArrayList();
        table.setItems(listFollow);
        table.goToPage(currentPage);
        table.setCurrentPage(currentPage);
        clearInputes();
    }

    private void trackingException(Exception e, String ErrorMessage) {
        e.getStackTrace();
        System.out.println(e.getMessage());
        dialogsController.openInfo(ErrorMessage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                trackingException(e, "حدث خطأ في عملية الإضافة");
            }
        });
        delete.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            context.delete(PATIENT).where(PATIENT.ID.eq(ID)).execute();
            listFollow = FXCollections.observableArrayList();
            table.setItems(listFollow);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            refrechLayout();
        });
        update.setOnAction(actionEvent -> {
            try {
                PatientRecord patientRecord = initRecord();
                patientRecord.setId(ID);
                patientRecord.update();
                refrechLayout();
                dialogsController.openInfo("تم عملية التعديل بنجاح");
            } catch (Exception e) {
                trackingException(e, "حدث خطأ في عملية التعديل");
            }
        });
    }

    private void setupTable() {
        MFXTableColumn<FollowModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(FollowModel::getId));
        MFXTableColumn<FollowModel> PatientColumn = new MFXTableColumn<>(" المريض", true, Comparator.comparing(FollowModel::getPatientFullName));
        MFXTableColumn<FollowModel> SicknessColumn = new MFXTableColumn<>("المرض", true, Comparator.comparing(FollowModel::getSickness));
        MFXTableColumn<FollowModel> DrColumn = new MFXTableColumn<>("الطبيب", true, Comparator.comparing(FollowModel::getDrFullName));
        MFXTableColumn<FollowModel> Pscolumn = new MFXTableColumn<>("المعالج النفسي", true, Comparator.comparing(FollowModel::getPsFullName));
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
                new StringFilter<>("المعالج النفسي", FollowModel::getPsFullName),
                new StringFilter<>("المرض", FollowModel::getSickness),
                new StringFilter<>("الحالة", FollowModel::getStatus),
                new StringFilter<>("تاريخ الدخول", FollowModel::getDateenterToString),
                new StringFilter<>("تاريخ الخروج", FollowModel::getDategoToString),
                new StringFilter<>("المرض", FollowModel::getSickness)
                );
        listFollow = FXCollections.observableArrayList(getAllFollows());
        table.setItems(listFollow);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}