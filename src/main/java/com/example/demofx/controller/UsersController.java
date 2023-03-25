package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.RoleRecord;
import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import com.example.demofx.model.UserModel;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.jooq.Record;
import org.jooq.Result;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.*;

public class UsersController implements Initializable {
    @FXML
    private MFXPaginatedTableView<UserModel> table;
    @FXML
    private MFXTextField Fname, Lname, phone, ID, srh;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXComboBox role, service, type;


    public static ObservableList<UserModel> listUsers;

    public static int currentPage;

    DialogsController dialogsController;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showUser(String data) {
        //username.setText(data);
    }

    private ArrayList<UserModel> getAllUser() {
        Result<?> result = context.select().from(USER).leftOuterJoin(SERVICE)
                .on(USER.IDSERVICE.eq(SERVICE.ID))
                .leftOuterJoin(ROLE)
                .on(USER.IDROLE.eq(ROLE.ID))
                .fetch();
        ArrayList<UserModel> listUser = new ArrayList<>();
        for (Record r : result) {
            ServiceRecord serviceRecord = r.into(SERVICE);
            UserRecord userRecord = r.into(USER);
            RoleRecord roleRecord = r.into(ROLE);
            UserModel userModel = new UserModel(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(),
                    userRecord.getFirstname(), userRecord.getLastname(), userRecord.getPhone(), userRecord.getType(), serviceRecord.getId(),
                    roleRecord.getId(), userRecord.getIdtype(), serviceRecord.getName(), roleRecord.getName());
            listUser.add(userModel);
        }
        return listUser;
    }

    private ArrayList<UserModel> searchUserByPhone(String phone) {
        ArrayList<UserModel> listUserFound = new ArrayList<>();
        listUsers.forEach(userModel -> {
            if(userModel.getPhone().equals(phone)){
                listUserFound.add(userModel);
            }
        });
        listUserFound.forEach(userModel -> {
            System.out.println(userModel);
        });
        return listUserFound;
    }

    private void fillInputs(UserRecord userRecord) {
        Fname.setText(userRecord.getFirstname());
        Lname.setText(userRecord.getLastname());
        phone.setText(userRecord.getPhone());
        ID.setText(userRecord.getId().toString());
    }

    private void clearInputes() {
        Fname.setText("");
        Lname.setText("");
        phone.setText("");
        ID.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<UserModel> users = new ArrayList<>();
        setupTable(users);
        table.autosizeColumnsOnInitialization();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            UserRecord userRecord = table.getSelectionModel().getSelectedValue();
            if (userRecord != null) {
                fillInputs(userRecord);
            }
        });
        srh.setOnKeyReleased(keyEvent -> {
            if (!srh.getText().isEmpty()) {
                table.setItems(FXCollections.observableArrayList(searchUserByPhone(srh.getText())));
            } else {
                listUsers = FXCollections.observableArrayList(getAllUser());
                table.setItems(listUsers);
            }
        });
        add.setOnAction(event -> {
            currentPage = table.getCurrentPage();
            UserRecord userRecord = DemoFX.context.newRecord(USER);
            userRecord.setFirstname(Fname.getText());
            userRecord.setLastname(Lname.getText());
            userRecord.setUsername(phone.getText());
            userRecord.setPassword(phone.getText());
            userRecord.setPhone(phone.getText());
            userRecord.setIdrole(1L);
            userRecord.setIdservice(1L);
            userRecord.setIdtype(1L);
            userRecord.store();
            listUsers=FXCollections.observableArrayList(getAllUser());
            table.setItems(listUsers);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
        delete.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            context.delete(USER).where(USER.ID.eq(Long.parseLong(ID.getText()))).execute();
            listUsers=FXCollections.observableArrayList(getAllUser());
            table.setItems(listUsers);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
        update.setOnAction(actionEvent -> {
            currentPage = table.getCurrentPage();
            UserRecord userRecord = DemoFX.context.newRecord(USER);
            userRecord.setFirstname(Fname.getText());
            userRecord.setLastname(Lname.getText());
            userRecord.setUsername(phone.getText());
            userRecord.setPassword(phone.getText());
            userRecord.setPhone(phone.getText());
            userRecord.setIdrole(1L);
            userRecord.setIdservice(1L);
            userRecord.setIdtype(1L);
            userRecord.setId(Long.parseLong(ID.getText()));
            userRecord.update();
            listUsers=FXCollections.observableArrayList(getAllUser());
            table.setItems(listUsers);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
    }

    private void setupTable(ArrayList<UserModel> users) {
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
        listUsers = FXCollections.observableArrayList(getAllUser());
        table.setItems(listUsers);
    }


}