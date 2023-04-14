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
    private static Long ID;





    private ArrayList<PatientModel> getAllPatients() {
        Result<?> result = context.select().from(PATIENT)
                .fetch();
        ArrayList<PatientModel> listUser = new ArrayList<>();
        for (Record r : result) {
            PatientModel patientRecord = new PatientModel();
            patientRecord.setAddress(r.getValue(PATIENT.ADDRESS));
            patientRecord.setLastname(r.getValue(PATIENT.LASTNAME));
            patientRecord.setFirstname(r.getValue(PATIENT.FIRSTNAME));
            patientRecord.setPhone(r.getValue(PATIENT.PHONE));
            patientRecord.setAge(r.getValue(PATIENT.AGE));
            patientRecord.setId(r.getValue(PATIENT.ID));
            patientRecord.setBirthday(r.getValue(PATIENT.BIRTHDAY));
            patientRecord.setWorke(r.getValue(PATIENT.WORKE));
            patientRecord.setGender(r.getValue(PATIENT.GENDER));
            patientRecord.setHeight(r.getValue(PATIENT.HEIGHT));
            patientRecord.setWieght(r.getValue(PATIENT.WIEGHT));
            patientRecord.setWorke(r.getValue(PATIENT.WORKE));
            patientRecord.setCivilstatus(r.getValue(PATIENT.CIVILSTATUS));
            listUser.add( patientRecord);
        }
        return listUser;
    }

    
    private void fillInputs(PatientRecord pateintRecord) {
        if(pateintRecord.getWorke()!=null){
            work.setText(pateintRecord.getWorke());
        }
        if(pateintRecord.getFirstname()!=null){
            Fname.setText(pateintRecord.getFirstname());
        }
        if(pateintRecord.getLastname()!=null){
            Lname.setText(pateintRecord.getLastname());
        }
        if(pateintRecord.getPhone()!=null){
            phone.setText(pateintRecord.getPhone());
        }
        if(pateintRecord.getAddress()!=null) {
            address.setText(pateintRecord.getAddress());
        }
        if(pateintRecord.getHeight()!=null) {
            height.setText(pateintRecord.getHeight()+"");
        }
        if(pateintRecord.getWieght()!=null) {
            weight.setText(pateintRecord.getHeight()+"");
        }
        if(pateintRecord.getBirthday()!=null){
            birthday.setText(pateintRecord.getBirthdayString());
        }
        if(pateintRecord.getGender()!=null && !pateintRecord.getGender().isEmpty()){
            genderCmbox.selectItem(pateintRecord.getGender());
        }
        if(pateintRecord.getCivilstatus()!=null && !pateintRecord.getCivilstatus().isEmpty()){
            civilStatusCmbox.selectItem(pateintRecord.getCivilstatus());
        }
        ID=Long.parseLong(pateintRecord.getId().toString());
    }

    private void clearInputes() {
        Fname.setText("");
        Lname.setText("");
        phone.setText("");
        address.setText("");
        height.setText("");
        weight.setText("");
        work.setText("");
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
        PatientRecord pateintRecord = DemoFX.context.newRecord(PATIENT);
        if(Fname.getText().isEmpty()){
            Fname.setStyle("-fx-border-color: #b61515");
        }else{
            pateintRecord.setFirstname(Fname.getText());
            Fname.setStyle("-fx-border-color: transparent");
        }
        pateintRecord.setLastname(Lname.getText());
        pateintRecord.setWieght(Double.parseDouble(weight.getText()));
        pateintRecord.setHeight(Integer.parseInt(height.getText()));
        pateintRecord.setPhone(phone.getText());
        pateintRecord.setGender(genderCmbox.getSelectionModel().getSelectedItem().toString());
        pateintRecord.setCivilstatus(civilStatusCmbox.getSelectionModel().getSelectedItem().toString());
        pateintRecord.setAddress(address.getText());
        pateintRecord.setWorke(work.getText());
        pateintRecord.setBirthday(birthday.getValue());
        return  pateintRecord;
    }

    private void refrechLayout(){
        listPatients=FXCollections.observableArrayList(getAllPatients());
        table.setItems(listPatients);
        table.goToPage(currentPage);
        table.setCurrentPage(currentPage);
        clearInputes();
    }

    private void trackingException(Exception e ,String ErrorMessage){
        e.getStackTrace();
        System.out.println(e.getMessage());
        dialogsController.openInfo(ErrorMessage);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogsController=new DialogsController();
        loadDataToLayout();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            PatientRecord patientRecord = table.getSelectionModel().getSelectedValue();
            if (patientRecord != null) {
                fillInputs(patientRecord);
            }
        });
        add.setOnAction(event -> {
            try {
                initRecord().store();
                refrechLayout();
                dialogsController.openInfo("تم عملية الإضافة بنجاح");
            }catch (Exception e){
                trackingException(e,"حدث خطأ في عملية الإضافة");
            }
        });
        delete.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            context.delete(PATIENT).where(PATIENT.ID.eq(ID)).execute();
            listPatients=FXCollections.observableArrayList(getAllPatients());
            table.setItems(listPatients);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
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
                trackingException(e,"حدث خطأ في عملية التعديل");
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
                new LongFilter<>("ID", PatientRecord::getId),
                new StringFilter<>("الإسم", PatientRecord::getFirstname),
                new StringFilter<>("اللقب", PatientRecord::getLastname),
                new StringFilter<>("العنوان", PatientRecord::getAddress),
                new StringFilter<>("الهاتف", PatientRecord::getPhone),
                //new StringFilter<>("العمر", PatientRecord::getWorke),
                new IntegerFilter<>("الطول", PatientRecord::getHeight),
                new DoubleFilter<>("الوزن", PatientRecord::getWieght),
                new StringFilter<>("العمر", PatientRecord::getWorke),
                new IntegerFilter<>("العمل", PatientRecord::getAge),
                new StringFilter<>("الحالة المدنية", PatientRecord::getCivilstatus),
                new StringFilter<>("الجنس", PatientRecord::getGender),
                new StringFilter<>("تاريخ الميلاد", PatientRecord::getBirthdayString)
        );
        listPatients = FXCollections.observableArrayList(getAllPatients());
        table.setItems(listPatients);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}