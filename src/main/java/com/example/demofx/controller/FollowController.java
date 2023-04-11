package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.RoleRecord;
import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import com.example.demofx.databaseManger.jooq.tables.records.TypeRecord;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import com.example.demofx.model.RoleModel;
import com.example.demofx.model.ServiceModel;
import com.example.demofx.model.TypeModel;
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

public class FollowController implements Initializable {
    @FXML
    private MFXPaginatedTableView<UserModel> table;
    @FXML
    private MFXTextField Fname;
    @FXML
    private MFXTextField Lname, phone, ID, srh;
    @FXML
    private MFXButton delete, add, update;
    @FXML
    private MFXComboBox<TypeModel> typeCmbox;
    @FXML
    private MFXFilterComboBox<RoleModel> roleCmbox;
    @FXML
    private MFXFilterComboBox<ServiceModel> serviceCmbox;
    public static ObservableList<UserModel> listUsers;

    public static int currentPage;

    DialogsController dialogsController;

    private Stage stage;

    public void setStage() {
         //stage = (Stage) add.getScene().getWindow();
    }

    public Stage getStage() {
        return stage;
    }

    private ArrayList<UserModel> getAllUser() {
        Result<?> result = context.select().from(USER).leftOuterJoin(SERVICE)
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
        return listUser;
    }



    private ObservableList<ServiceModel> getAllServices() {
        ObservableList<ServiceModel> listServices=FXCollections.observableArrayList(new ServiceModel());
        listServices.remove(0);
        Result<?> result = context.select().from(SERVICE).fetch();
        for (Record r : result) {
            ServiceModel serviceModel =new ServiceModel(r.getValue(SERVICE.ID),r.getValue(SERVICE.NAME));
            listServices.add(serviceModel);
        }
        return listServices;
    }

    private ObservableList<TypeModel> getAllTypes() {
        ObservableList<TypeModel> listTypes=FXCollections.observableArrayList(new TypeModel());
        listTypes.remove(0);
        Result<?> result = context.select().from(TYPE).fetch();
        for (Record r : result) {
            TypeModel typeModel =new TypeModel(r.getValue(TYPE.ID),r.getValue(TYPE.NAME));
            listTypes.add(typeModel);
        }
        return listTypes;
    }

    private ObservableList<RoleModel> getAllRoles() {
        ObservableList<RoleModel> listRoles=FXCollections.observableArrayList(new RoleModel());
        listRoles.remove(0);
        Result<?> result = context.select().from(ROLE).fetch();
        for (Record r : result) {
            RoleModel roleModel =new RoleModel(r.getValue(ROLE.ID),r.getValue(ROLE.NAME));
            listRoles.add(roleModel);
        }
        return listRoles;
    }


    /*public long getIdFromName(String name, String table) {
        switch (table){
            case "type":{
                return context.select().from(TYPE).where(TYPE.NAME.eq(name)).fetchOne().getValue(TYPE.ID);
            }
            case "service":{
                return context.select().from(SERVICE).where(SERVICE.NAME.eq(name)).fetchOne().getValue(SERVICE.ID);
            }
            case "role":{
                return context.select().from(ROLE).where(ROLE.NAME.eq(name)).fetchOne().getValue(ROLE.ID);
            }
            default:{
                return  0L;
            }
        }
    }*/

    private ArrayList<UserModel> searchUserByPhone(String phone) {
        ArrayList<UserModel> listUserFound = new ArrayList<>();
        listUsers.forEach(userModel -> {
            if(userModel.getPhone().contains(phone)){
                listUserFound.add(userModel);
            }
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

    private void loadDataToLayout(){
        setupTable();
        table.autosizeColumnsOnInitialization();
        serviceCmbox.setItems(getAllServices());
        typeCmbox.setItems(getAllTypes());
        roleCmbox.setItems(getAllRoles());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogsController=new DialogsController();
        loadDataToLayout();
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
                table.getItems().clear();
                listUsers.forEach(userModel -> {
                    table.getItems().add(userModel);
                });
                table.goToPage(0);
                table.setCurrentPage(0);
            }
        });
        add.setOnAction(event -> {
            try {
                currentPage = table.getCurrentPage();
                UserRecord userRecord = DemoFX.context.newRecord(USER);
                userRecord.setFirstname(Fname.getText());
                userRecord.setLastname(Lname.getText());
                userRecord.setUsername(phone.getText());
                userRecord.setPassword(phone.getText());
                userRecord.setPhone(phone.getText());
                userRecord.setIdrole(roleCmbox.getSelectionModel().getSelectedItem().getValue(ROLE.ID));
                userRecord.setIdservice(serviceCmbox.getSelectionModel().getSelectedItem().getValue(SERVICE.ID));
                userRecord.setIdtype(typeCmbox.getSelectionModel().getSelectedItem().getValue(TYPE.ID));
                userRecord.setType(typeCmbox.getSelectionModel().getSelectedItem().toString());
                userRecord.store();
                listUsers=FXCollections.observableArrayList(getAllUser());
                table.setItems(listUsers);
                table.goToPage(currentPage);
                table.setCurrentPage(currentPage);
                clearInputes();
                dialogsController.openInfo("تم عملية الإضافة بنجاح");
            }catch (Exception e){
                //dialogsController.openInfo("حدث خطأ في عملية الإضافة");
            }
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
            userRecord.setIdrole(roleCmbox.getSelectionModel().getSelectedItem().getValue(ROLE.ID));
            userRecord.setIdservice(serviceCmbox.getSelectionModel().getSelectedItem().getValue(SERVICE.ID));
            userRecord.setIdtype(typeCmbox.getSelectionModel().getSelectedItem().getValue(TYPE.ID));
            userRecord.setType(typeCmbox.getSelectionModel().getSelectedItem().toString());
            userRecord.setId(Long.parseLong(ID.getText()));
            userRecord.update();
            listUsers=FXCollections.observableArrayList(getAllUser());
            table.setItems(listUsers);
            table.goToPage(currentPage);
            table.setCurrentPage(currentPage);
            clearInputes();
        });
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
        listUsers = FXCollections.observableArrayList(getAllUser());
        table.setItems(listUsers);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}