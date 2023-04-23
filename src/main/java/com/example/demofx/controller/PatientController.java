package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import com.example.demofx.model.PatientModel;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
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
import static com.example.demofx.databaseManger.jooq.Tables.PATIENT;

public class PatientController implements Initializable {
    @FXML
    private MFXPaginatedTableView<PatientModel> table;
    @FXML
    private MFXTextField Fname, Lname, phone, address,height,weight,work;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXDatePicker birthday;
    @FXML
    private MFXComboBox<String> scientificLevelCmbox,socioEconomicLevelCmbox,genderCmbox,civilStatusCmbox;

    public static ObservableList<PatientModel> listPatients;

    public static int currentPage;

    DialogsController dialogsController;

    private ArrayList<String> genderList ;
    private ArrayList<String> civilStatusList ;
    private static Long ID=0L;

    static PatientModel patientModel ;



    public static SimpleStringProperty fNameProperty,lNameProperty,addressProperty,phoneProperty,weightProperty,workProperty,heightProperty;

    private void initDataBinding(){
        fNameProperty=new SimpleStringProperty();
        lNameProperty=new SimpleStringProperty();
        phoneProperty =new SimpleStringProperty();
        addressProperty=new SimpleStringProperty();
        weightProperty=new SimpleStringProperty();
        workProperty=new SimpleStringProperty();
        heightProperty=new SimpleStringProperty();
        //binding Property with fields
        fNameProperty.bindBidirectional(Fname.textProperty());
        lNameProperty.bindBidirectional(Lname.textProperty());
        phoneProperty.bindBidirectional(phone.textProperty());
        addressProperty.bindBidirectional(address.textProperty());
        weightProperty.bindBidirectional(weight.textProperty());
        heightProperty.bindBidirectional(height.textProperty());
        workProperty.bindBidirectional(work.textProperty());
    }

    private void fillInputs(PatientModel patientModel) {
        if(patientModel.getWorke()!=null){
            workProperty.set(patientModel.getWorke());
        }
        if(patientModel.getFirstname()!=null){
            fNameProperty.set(patientModel.getFirstname());
        }
        if(patientModel.getLastname()!=null){
            lNameProperty.set(patientModel.getLastname());
        }
        if(patientModel.getPhone()!=null){
            phoneProperty.set(patientModel.getPhone());
        }
        if(patientModel.getAddress()!=null) {
            addressProperty.set(patientModel.getAddress());
        }
        if(patientModel.getHeight()!=null) {
            heightProperty.set(patientModel.getHeight()+"");
        }
        if(patientModel.getWieght()!=null) {
            weightProperty.set(patientModel.getHeight()+"");
        }
        if(patientModel.getBirthday()!=null){
            birthday.setText(patientModel.getBirthdayString());
        }
        if(patientModel.getGender()!=null && !patientModel.getGender().isEmpty()){
            genderCmbox.selectItem(patientModel.getGender());
        }
        if(patientModel.getCivilstatus()!=null && !patientModel.getCivilstatus().isEmpty()){
            civilStatusCmbox.selectItem(patientModel.getCivilstatus());
        }
        ID=Long.parseLong(patientModel.getId().toString());
    }

    private void clearInputes() {
        fNameProperty.set("");
        lNameProperty.set("");
        phoneProperty.set("");
        addressProperty.set("");
        heightProperty.set("");
        weightProperty.set("");
        workProperty.set("");
        birthday.setText("");
        //genderCmbox.selectItem("");
        //civilStatusCmbox.selectItem("");
        ID=0L;
    }

    private void loadDataToLayout(){
        genderList = new ArrayList<>();
        civilStatusList= new ArrayList<>();
        civilStatusList.add("اغزب");
        civilStatusList.add("متزوج");
        civilStatusList.add("أرمل");
        civilStatusList.add("مطلق");
        genderList.add("ذكر");
        genderList.add("أنثى");
        setupTable();
        table.autosizeColumnsOnInitialization();
        genderCmbox.getItems().addAll(FXCollections.observableArrayList(genderList));
        civilStatusCmbox.getItems().addAll(FXCollections.observableArrayList(civilStatusList));
    }

    private PatientRecord initRecord(){
        currentPage = table.getCurrentPage();
        PatientRecord patientRecord = DemoFX.context.newRecord(PATIENT);
        patientRecord.setFirstname(fNameProperty.getValue());
        patientRecord.setLastname(lNameProperty.getValue());
        patientRecord.setWieght(Double.parseDouble(weightProperty.getValue()));
        patientRecord.setHeight(Integer.parseInt(heightProperty.getValue()));
        patientRecord.setPhone(phoneProperty.getValue());
        patientRecord.setGender(genderCmbox.getSelectionModel().getSelectedItem().toString());
        patientRecord.setCivilstatus(civilStatusCmbox.getSelectionModel().getSelectedItem().toString());
        patientRecord.setAddress(addressProperty.getValue());
        patientRecord.setWorke(workProperty.getValue());
        patientRecord.setBirthday(birthday.getValue());
        return  patientRecord;
    }

    private void refrechLayout(){
        listPatients=FXCollections.observableArrayList(patientModel.getAllPatients());
        table.setItems(listPatients);
        table.goToPage(currentPage);
        table.setCurrentPage(currentPage);
        clearInputes();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogsController=new DialogsController();
        patientModel=new PatientModel();
        initDataBinding();
        loadDataToLayout();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            PatientModel patientModel = table.getSelectionModel().getSelectedValue();
            if (patientModel != null) {
                fillInputs(patientModel);
            }
        });
        add.setOnAction(event -> {
            try {
                initRecord().store();
                refrechLayout();
                dialogsController.openInfo("تم عملية الإضافة بنجاح");
            }catch (Exception e){
                Utils.trackingException(e,"حدث خطأ في عملية الإضافة",dialogsController);
            }
        });
        delete.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            context.delete(PATIENT).where(PATIENT.ID.eq(ID)).execute();
            refrechLayout();
        });
        update.setOnAction(actionEvent -> {
            try {
                PatientRecord patientRecord=initRecord();
                patientRecord.setId(ID);
                patientRecord.update();
                refrechLayout();
                dialogsController.openInfo("تم عملية التعديل بنجاح");
            }catch (Exception e){
                Utils.trackingException(e,"حدث خطأ في عملية الإضافة",dialogsController);
            }
        });
    }

    private void setupTable() {
        MFXTableColumn<PatientModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(PatientModel::getId));
        MFXTableColumn<PatientModel> FnameColumn = new MFXTableColumn<>("الإسم", true, Comparator.comparing(PatientModel::getFirstname));
        MFXTableColumn<PatientModel> LnameColumn = new MFXTableColumn<>("اللقب", true, Comparator.comparing(PatientModel::getLastname));
        MFXTableColumn<PatientModel> PhoneColumn = new MFXTableColumn<>("الهاتف", true, Comparator.comparing(PatientModel::getPhone));
        MFXTableColumn<PatientModel> AddressColumn = new MFXTableColumn<>("العنوان", true, Comparator.comparing(PatientModel::getAddress));
        //MFXTableColumn<PatientModel> BirthdayColumn = new MFXTableColumn<>("العمر", true, Comparator.comparing(PatientModel::getAge));
        MFXTableColumn<PatientModel> WorkColumn = new MFXTableColumn<>("العمل", true, Comparator.comparing(PatientModel::getWorke));
        MFXTableColumn<PatientModel> HeightColumn = new MFXTableColumn<>("الطول", true, Comparator.comparing(PatientModel::getHeight));
        MFXTableColumn<PatientModel> WeightColumn = new MFXTableColumn<>("الوزن", true, Comparator.comparing(PatientModel::getWieght));
        MFXTableColumn<PatientModel> GenderColumn = new MFXTableColumn<>("الجنس", true, Comparator.comparing(PatientModel::getGender));
        MFXTableColumn<PatientModel> statusCivilColumn = new MFXTableColumn<>("الحالة المدنية", true, Comparator.comparing(PatientModel::getCivilstatus));
        MFXTableColumn<PatientModel> dateColumn = new MFXTableColumn<>("تاريخ الميلاد", true, Comparator.comparing(PatientModel::getBirthday));


        AddressColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getAddress));
        WorkColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getWorke));
        //BirthdayColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getAge));
        PhoneColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getPhone));
        LnameColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getLastname));
        FnameColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getFirstname));
        Idcolumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getId));
        HeightColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getHeight));
        WeightColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getWieght));
        statusCivilColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getCivilstatus));
        GenderColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getGender));
        dateColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getBirthday));


        table.getTableColumns().addAll(Idcolumn, FnameColumn, LnameColumn,GenderColumn,statusCivilColumn, PhoneColumn, AddressColumn, WeightColumn,HeightColumn,
                dateColumn,WorkColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", PatientModel::getId),
                new StringFilter<>("الإسم", PatientModel::getFirstname),
                new StringFilter<>("اللقب", PatientModel::getLastname),
                new StringFilter<>("العنوان", PatientModel::getAddress),
                new StringFilter<>("الهاتف", PatientModel::getPhone),
                //new StringFilter<>("العمر", PatientRecord::getWorke),
                new IntegerFilter<>("الطول", PatientModel::getHeight),
                new DoubleFilter<>("الوزن", PatientModel::getWieght),
                new StringFilter<>("العمر", PatientModel::getWorke),
                new IntegerFilter<>("العمل", PatientModel::getAge),
                new StringFilter<>("الحالة المدنية", PatientModel::getCivilstatus),
                new StringFilter<>("الجنس", PatientModel::getGender),
                new StringFilter<>("تاريخ الميلاد", PatientModel::getBirthdayString)
        );
        listPatients = FXCollections.observableArrayList(patientModel.getAllPatients());
        table.setItems(listPatients);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}