package com.example.demofx.controller;

import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private MFXTextField search;



    @FXML
    private MFXPaginatedTableView<UserRecord> table;

    public static  ObservableList<UserRecord> user ;



    public void showUser(String data) {
        //username.setText(data);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<UserRecord> users = new ArrayList<>();

        users.add(new UserRecord(1L,"user1","user1","user1","user1","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));
        users.add(new UserRecord(2L,"user2","user2","user2","user2","","",1L,1L,1L));

        setupTable(users);
        table.autosizeColumnsOnInitialization();
        search.setOnKeyReleased(event -> {
            if(search.getText().isEmpty()){
                user = FXCollections.observableArrayList(users);
                table.setItems(user);
            }else{
                user = FXCollections.observableArrayList(users.get(Integer.parseInt(search.getText())));
                table.setItems(user);
            }
        });
    }

    private void setupTable(ArrayList<UserRecord> users) {
        MFXTableColumn<UserRecord> name2Column = new MFXTableColumn<>("ID", true, Comparator.comparing(UserRecord::getId));
        MFXTableColumn<UserRecord> nameColumn = new MFXTableColumn<>("الإسم", true, Comparator.comparing(UserRecord::getFirstname));
        MFXTableColumn<UserRecord> name1Column = new MFXTableColumn<>("اللقب", true, Comparator.comparing(UserRecord::getLastname));

        nameColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getFirstname));
        name1Column.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getLastname));
        name2Column.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getId));

        table.getTableColumns().addAll(name2Column,nameColumn, name1Column);
        table.getFilters().addAll(
                new LongFilter<>("ID", UserRecord::getId),
                new StringFilter<>("الإسم", UserRecord::getFirstname),
                new StringFilter<>("اللقب", UserRecord::getLastname)
        );
        user = FXCollections.observableArrayList(users);
        table.setItems(user);
    }




}