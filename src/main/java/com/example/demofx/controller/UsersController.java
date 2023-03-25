package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.User;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
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
import static com.example.demofx.databaseManger.jooq.Tables.USER;

public class UsersController implements Initializable {
    @FXML
    private MFXPaginatedTableView<UserRecord> table;
    @FXML
    private MFXTextField Fname, Lname, phone, ID,srh;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXComboBox role, service, type;


    public static ObservableList<UserRecord> user;

    public static int currentPage;

    DialogsController dialogsController;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showUser(String data) {
        //username.setText(data);
    }

    private ArrayList<UserRecord> getAllUser() {
        Result<Record> result = context.select().from(User.USER).fetch();
        ArrayList<UserRecord> listUser = new ArrayList<>();
        for (Record r : result) {
            UserRecord userRecord = new UserRecord();
            userRecord.setPhone(r.getValue(USER.PHONE));
            userRecord.setLastname(r.getValue(USER.LASTNAME));
            userRecord.setFirstname(r.getValue(USER.FIRSTNAME));
            userRecord.setType(r.getValue(USER.TYPE));
            userRecord.setId(r.getValue(USER.ID));
            userRecord.setIdrole(r.getValue(USER.IDROLE));
            userRecord.setIdservice(r.getValue(USER.IDSERVICE));
            userRecord.setIdtype(r.getValue(USER.IDTYPE));
            listUser.add(userRecord);
        }
        return listUser;
    }

    private ArrayList<UserRecord> searchUserByPhone(String phone) {
        Result<Record> result = context.select().from(User.USER).where(USER.PHONE.contains(phone.toLowerCase())).fetch();
        ArrayList<UserRecord> listUser = new ArrayList<>();
        for (Record r : result) {
            UserRecord userRecord = new UserRecord();
            userRecord.setPhone(r.getValue(USER.PHONE));
            userRecord.setLastname(r.getValue(USER.LASTNAME));
            userRecord.setFirstname(r.getValue(USER.FIRSTNAME));
            userRecord.setType(r.getValue(USER.TYPE));
            userRecord.setId(r.getValue(USER.ID));
            userRecord.setIdrole(r.getValue(USER.IDROLE));
            userRecord.setIdservice(r.getValue(USER.IDSERVICE));
            userRecord.setIdtype(r.getValue(USER.IDTYPE));
            listUser.add(userRecord);
        }
        return listUser;
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
        //dialogsController= new DialogsController(this.stage);
        //dialogsController.openInfo();
        ArrayList<UserRecord> users = new ArrayList<>();
        setupTable(users);
        table.autosizeColumnsOnInitialization();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            UserRecord userRecord = table.getSelectionModel().getSelectedValue();
            if (userRecord != null) {
                fillInputs(userRecord);
            }
        });
        srh.setOnKeyReleased(keyEvent -> {
            if(!srh.getText().isEmpty()){
                user = FXCollections.observableArrayList(searchUserByPhone(srh.getText()));
                table.setItems(user);
            }else{
                user = FXCollections.observableArrayList(getAllUser());
                table.setItems(user);
            }
        });
        add.setOnAction(event -> {
            currentPage=table.getCurrentPage();
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
            user = FXCollections.observableArrayList(getAllUser());
            table.setItems(user);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
        delete.setOnAction(actionEvent -> {

            currentPage=table.getCurrentPage();
            context.delete(USER).where(USER.ID.eq(Long.parseLong(ID.getText()))).execute();
            user = FXCollections.observableArrayList(getAllUser());
            table.setItems(user);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
        update.setOnAction(actionEvent -> {
            currentPage=table.getCurrentPage();
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
            user = FXCollections.observableArrayList(getAllUser());
            table.setItems(user);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
    }

    private void setupTable(ArrayList<UserRecord> users) {
        MFXTableColumn<UserRecord> Idcolumn = new MFXTableColumn<>("ID", true, Comparator.comparing(UserRecord::getId));
        MFXTableColumn<UserRecord> FnameColumn = new MFXTableColumn<>("الإسم", true, Comparator.comparing(UserRecord::getFirstname));
        MFXTableColumn<UserRecord> LnameColumn = new MFXTableColumn<>("اللقب", true, Comparator.comparing(UserRecord::getLastname));
        MFXTableColumn<UserRecord> PhoneColumn = new MFXTableColumn<>("الهاتف", true, Comparator.comparing(UserRecord::getPhone));
        MFXTableColumn<UserRecord> RoleColumn = new MFXTableColumn<>("التصريح", true, Comparator.comparing(UserRecord::getIdrole));
        MFXTableColumn<UserRecord> TypeColumn = new MFXTableColumn<>("النوع", true, Comparator.comparing(UserRecord::getType));
        MFXTableColumn<UserRecord> ServiceColumn = new MFXTableColumn<>("المصلحة", true, Comparator.comparing(UserRecord::getIdservice));

        RoleColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getIdrole));
        PhoneColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getPhone));
        TypeColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getType));
        ServiceColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getIdservice));
        FnameColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getFirstname));
        LnameColumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getLastname));
        Idcolumn.setRowCellFactory(user -> new MFXTableRowCell<>(UserRecord::getId));

        table.getTableColumns().addAll(Idcolumn, FnameColumn, LnameColumn, PhoneColumn, RoleColumn, TypeColumn, ServiceColumn);
        table.getFilters().addAll(
                new LongFilter<>("ID", UserRecord::getId),
                new StringFilter<>("الإسم", UserRecord::getFirstname),
                new StringFilter<>("اللقب", UserRecord::getLastname),
                new StringFilter<>("النوع", UserRecord::getType),
                new StringFilter<>("الهاتف", UserRecord::getPhone)
        );
        user = FXCollections.observableArrayList(getAllUser());
        table.setItems(user);
    }


}