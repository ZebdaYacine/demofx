package com.example.demofx.controller;

import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import com.example.demofx.model.PatientModel;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML
    private MFXPaginatedTableView<PatientModel> table;
    @FXML
    private MFXTextField Fname, Lname, phone, ID, address,height,weight,work;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXComboBox<String> scientificLevelCmbox,socioEconomicLevelCmbox,genderCmbox,civilStatusCmbox;

    public static ObservableList<PatientModel> listPatients;

    public static int currentPage;

    DialogsController dialogsController;

   

   /* private ArrayList<UserModel> getAllUser() {
       *//* Result<?> result = context.select().from(USER).leftOuterJoin(SERVICE)
                .on(USER.IDSERVICE.eq(SERVICE.ID))
                .leftOuterJoin(ROLE)
                .on(USER.IDROLE.eq(ROLE.ID))
                .leftOuterJoin(TYPE)
                .on(TYPE.ID.eq(USER.IDTYPE))
                .fetch();
        ArrayList<UserModel> listUser = new ArrayList<>();
        for (Record r : result) {
            ServiceRecord serviceRecord = r.into(SERVICE);
            UserRecord userRecord = r.into(USER);
            RoleRecord roleRecord = r.into(ROLE);
            TypeRecord typeRecord = r.into(TYPE);
            UserModel userModel = new UserModel(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(),
                    userRecord.getFirstname(), userRecord.getLastname(), userRecord.getPhone(), typeRecord.getName(), serviceRecord.getId(),
                    roleRecord.getId(), userRecord.getIdtype(), serviceRecord.getName(), roleRecord.getName());
            listUser.add(userModel);
        }
        return listUser;*//*
    }*/

    
    private void fillInputs(PatientRecord pateintRecord) {
        Fname.setText(pateintRecord.getFirstname());
        Lname.setText(pateintRecord.getLastname());
        phone.setText(pateintRecord.getPhone());
        ID.setText(pateintRecord.getId().toString());
    }

    private void clearInputes() {
        Fname.setText("");
        Lname.setText("");
        phone.setText("");
        ID.setText("");
    }

    private void loadDataToLayout(){
        setupTable();
        table.autosizeColumnsOnInitialization();
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

        });
        delete.setOnAction(actionEvent -> {

        });
        update.setOnAction(actionEvent -> {

        });
    }

    private void setupTable() {
        MFXTableColumn<PatientModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(PatientModel::getId));
        MFXTableColumn<PatientModel> FnameColumn = new MFXTableColumn<>("الإسم", true, Comparator.comparing(PatientModel::getFirstname));
        MFXTableColumn<PatientModel> LnameColumn = new MFXTableColumn<>("اللقب", true, Comparator.comparing(PatientModel::getLastname));
        MFXTableColumn<PatientModel> PhoneColumn = new MFXTableColumn<>("الهاتف", true, Comparator.comparing(PatientModel::getPhone));
        MFXTableColumn<PatientModel> AddressColumn = new MFXTableColumn<>("العنوان", true, Comparator.comparing(PatientModel::getAddress));
        MFXTableColumn<PatientModel> BirthdayColumn = new MFXTableColumn<>("العمر", true, Comparator.comparing(PatientModel::getAge));
        //MFXTableColumn<PatientModel> WorkColumn = new MFXTableColumn<>("العمل", true, Comparator.comparing(PatientModel::getWorke));
        MFXTableColumn<PatientModel> HeightColumn = new MFXTableColumn<>("الطول", true, Comparator.comparing(PatientModel::getHeight));
        MFXTableColumn<PatientModel> WeightColumn = new MFXTableColumn<>("الوزن", true, Comparator.comparing(PatientModel::getWieght));



        AddressColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getAddress));
        //WorkColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getWorke));
        BirthdayColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getAge));
        PhoneColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getPhone));
        LnameColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getLastname));
        FnameColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getFirstname));
        Idcolumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getId));
        HeightColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getHeight));
        WeightColumn.setRowCellFactory(patientModel -> new MFXTableRowCell<>(PatientModel::getWieght));

        table.getTableColumns().addAll(Idcolumn, FnameColumn, LnameColumn, PhoneColumn, AddressColumn, WeightColumn,HeightColumn, BirthdayColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", PatientRecord::getId),
                new StringFilter<>("الإسم", PatientRecord::getFirstname),
                new StringFilter<>("اللقب", PatientRecord::getLastname),
                new StringFilter<>("العنوان", PatientRecord::getAddress),
                new StringFilter<>("الهاتف", PatientRecord::getPhone),
                new StringFilter<>("العمل", PatientRecord::getWorke),
                new IntegerFilter<>("الطول", PatientRecord::getHeight),
                new DoubleFilter<>("الوزن", PatientRecord::getWieght),
                new IntegerFilter<>("العمر", PatientRecord::getAge)
                );
       /* listPatients = FXCollections.observableArrayList(getAllUser());
        table.setItems(listPatients);
        table.goToPage(0);
        table.setCurrentPage(0);*/
    }
}