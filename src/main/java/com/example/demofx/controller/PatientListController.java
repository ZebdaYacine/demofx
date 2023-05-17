package com.example.demofx.controller;

import com.example.demofx.model.PatientModel;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class PatientListController implements Initializable {
    @FXML
    private MFXPaginatedTableView<PatientModel> table;

    public static ObservableList<PatientModel> listPatients;

    static PatientModel patientModel ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
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