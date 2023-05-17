package com.example.demofx.controller;

import com.example.demofx.model.ServiceModel;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
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

public class ServicesListController implements Initializable {
    @FXML
    private MFXPaginatedTableView<ServiceModel> table;


    public static ObservableList<ServiceModel> listPatients;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
    }

    private void setupTable() {
        MFXTableColumn<ServiceModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(ServiceModel::getId));
        MFXTableColumn<ServiceModel> FnameColumn = new MFXTableColumn<>("الإسم المصلحة", true, Comparator.comparing(ServiceModel::getName));
        FnameColumn.setRowCellFactory(serviceModel -> new MFXTableRowCell<>(ServiceModel::getName));
        Idcolumn.setRowCellFactory(serviceModel -> new MFXTableRowCell<>(ServiceModel::getId));

        table.getTableColumns().addAll(Idcolumn, FnameColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", ServiceModel::getId),
                new StringFilter<>("الإسم المصلحة", ServiceModel::getName)
        );
        listPatients = FXCollections.observableArrayList(ServiceModel.getAllServices());
        table.setItems(listPatients);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}