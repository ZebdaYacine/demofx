package com.example.demofx.controller;

import com.example.demofx.DemoFX;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import com.example.demofx.model.RoleModel;
import com.example.demofx.model.ServiceModel;
import com.example.demofx.model.TypeModel;
import com.example.demofx.model.UserModel;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.*;

public class UsersController implements Initializable {
    @FXML
    private MFXPaginatedTableView<UserModel> table;
    @FXML
    private MFXTextField Fname, Lname, phone;
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

    static UserModel userModel ;

    private static long ID=0;

    public static SimpleStringProperty fNameProperty,lNameProperty,phoneProperty,srhProperty;


    private void initDataBinding(){
        fNameProperty=new SimpleStringProperty();
        lNameProperty=new SimpleStringProperty();
        phoneProperty =new SimpleStringProperty();
        srhProperty=new SimpleStringProperty();
        //binding Property with fields
        fNameProperty.bindBidirectional(Fname.textProperty());
        lNameProperty.bindBidirectional(Lname.textProperty());
        phoneProperty.bindBidirectional(phone.textProperty());
/*
        srhProperty.bindBidirectional(srh.textProperty());
*/
    }

    private void fillInputs(UserRecord userRecord) {
        fNameProperty.set(userRecord.getFirstname());
        lNameProperty.set(userRecord.getLastname());
        phoneProperty.set(userRecord.getPhone());
        ID=userRecord.getId();
    }

    private void clearInputes() {
        Fname.setText("");
        Lname.setText("");
        phone.setText("");
        ID=0;
    }

    private void loadDataToLayout(){
        setupTable();
        table.autosizeColumnsOnInitialization();
        serviceCmbox.setItems(userModel.getAllServices());
        typeCmbox.setItems(userModel.getAllTypes());
        roleCmbox.setItems(userModel.getAllRoles());
    }
    private void refrechLayout(){
        listUsers=FXCollections.observableArrayList(userModel.getAllUser());
        table.setItems(listUsers);
        table.goToPage(currentPage);
        table.setCurrentPage(currentPage);
        clearInputes();
    }
    private UserRecord initRecord(){
        UserRecord userModel = DemoFX.context.newRecord(USER);
        currentPage = table.getCurrentPage();
        userModel.setFirstname(fNameProperty.getValue());
        userModel.setLastname(lNameProperty.getValue());
        userModel.setUsername(phoneProperty.get());
        userModel.setPassword(phoneProperty.get());
        userModel.setPhone(phoneProperty.get());
        userModel.setIdrole(roleCmbox.getSelectionModel().getSelectedItem().getValue(ROLE.ID));
        userModel.setIdservice(serviceCmbox.getSelectionModel().getSelectedItem().getValue(SERVICE.ID));
        userModel.setIdtype(typeCmbox.getSelectionModel().getSelectedItem().getValue(TYPE.ID));
        userModel.setType(typeCmbox.getSelectionModel().getSelectedItem().toString());
        return userModel ;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogsController=new DialogsController();
        userModel = new UserModel();
        loadDataToLayout();
        initDataBinding();
        table.getSelectionModel().selectionProperty().addListener((observableValue, integerUserRecordObservableMap, row) -> {
            UserRecord userRecord = table.getSelectionModel().getSelectedValue();
            if (userRecord != null) {
                fillInputs(userRecord);
            }
        });
        /*srh.setOnKeyReleased(keyEvent -> {
            if (!srh.getText().isEmpty()) {
                table.setItems(FXCollections.observableArrayList(userModel.searchUserByPhone(srh.getText(),listUsers)));
            } else {
                table.getItems().clear();
                listUsers.forEach(userModel -> {
                    table.getItems().add(userModel);
                });
                table.goToPage(0);
                table.setCurrentPage(0);
            }
        });*/
        add.setOnAction(event -> {
            try {
                initRecord().store();
                refrechLayout();
                dialogsController.openInfo("تم عملية الإضافة بنجاح");
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "خطأ في  إتمام العملية  حاول مرة اخرى").show();
            }
        });
        delete.setOnAction(actionEvent -> {
            try {
                currentPage = table.getCurrentPage();
                context.delete(USER).where(USER.ID.eq(ID)).execute();
                refrechLayout();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "خطأ في  إتمام العملية  حاول مرة اخرى").show();
            }
        });
        update.setOnAction(actionEvent -> {
            try{
                UserRecord userRecord=initRecord();
                userRecord.setId(ID);
                userRecord.update();
                refrechLayout();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "خطأ في  إتمام العملية  حاول مرة اخرى").show();
            }
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
        listUsers = FXCollections.observableArrayList(userModel.getAllUser());
        table.setItems(listUsers);
        table.goToPage(0);
        table.setCurrentPage(0);
    }
}