package com.example.demofx.controller;

import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import com.example.demofx.model.UserModel;
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

public class UsersListController implements Initializable {
    @FXML
    private MFXPaginatedTableView<UserModel> table;
    public static ObservableList<UserModel> listUsers;
    static UserModel userModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       setupTable();
    }

    private void setupTable() {
        MFXTableColumn<UserModel> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(UserModel::getId));
        MFXTableColumn<UserModel> FnameColumn = new MFXTableColumn<>("الإسم", true, Comparator.comparing(UserModel::getFirstname));
        MFXTableColumn<UserModel> LnameColumn = new MFXTableColumn<>("اللقب", true, Comparator.comparing(UserModel::getLastname));
        MFXTableColumn<UserModel> PhoneColumn = new MFXTableColumn<>("الهاتف", true, Comparator.comparing(UserModel::getPhone));
        MFXTableColumn<UserModel> RoleColumn = new MFXTableColumn<>("التصريح", true, Comparator.comparing(UserModel::getRole));
        MFXTableColumn<UserModel> TypeColumn = new MFXTableColumn<>("النوع", true, Comparator.comparing(UserRecord::getType));
        MFXTableColumn<UserModel> ServiceColumn = new MFXTableColumn<>("المصلحة", true, Comparator.comparing(UserModel::getService));

        RoleColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getRole));
        PhoneColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getPhone));
        TypeColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getType));
        ServiceColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getService));
        FnameColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getFirstname));
        LnameColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getLastname));
        Idcolumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserModel::getId));

        table.getTableColumns().addAll(Idcolumn, FnameColumn, LnameColumn, PhoneColumn, RoleColumn, TypeColumn, ServiceColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", UserRecord::getId),
                new StringFilter<>("الإسم", UserRecord::getFirstname),
                new StringFilter<>("اللقب", UserRecord::getLastname),
                new StringFilter<>("النوع", UserRecord::getType),
                new StringFilter<>("الهاتف", UserRecord::getPhone)
        );
        listUsers = FXCollections.observableArrayList(userModel.getAllUserByType("doctor"));
        table.setItems(listUsers);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}