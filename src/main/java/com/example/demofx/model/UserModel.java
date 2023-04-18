package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.RoleRecord;
import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import com.example.demofx.databaseManger.jooq.tables.records.TypeRecord;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.*;

public class UserModel extends UserRecord {

    private String service;
    private String role;

    public UserModel(Long id, String username, String password, String firstname, String lastname, String phone, String type, Long idservice, Long idrole, Long idtype, String service, String role) {
        super(id, username, password, firstname, lastname, phone, type, idservice, idrole, idtype);
        this.service = service;
        this.role = role;
    }

    public UserModel() {
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<UserModel> getAllUser() {
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

    public ObservableList<ServiceModel> getAllServices() {
        ObservableList<ServiceModel> listServices= FXCollections.observableArrayList(new ServiceModel());
        listServices.remove(0);
        Result<?> result = context.select().from(SERVICE).fetch();
        for (Record r : result) {
            ServiceModel serviceModel =new ServiceModel(r.getValue(SERVICE.ID),r.getValue(SERVICE.NAME));
            listServices.add(serviceModel);
        }
        return listServices;
    }
    public ObservableList<TypeModel> getAllTypes() {
        ObservableList<TypeModel> listTypes=FXCollections.observableArrayList(new TypeModel());
        listTypes.remove(0);
        Result<?> result = context.select().from(TYPE).fetch();
        for (Record r : result) {
            TypeModel typeModel =new TypeModel(r.getValue(TYPE.ID),r.getValue(TYPE.NAME));
            listTypes.add(typeModel);
        }
        return listTypes;
    }
    public ObservableList<RoleModel> getAllRoles() {
        ObservableList<RoleModel> listRoles=FXCollections.observableArrayList(new RoleModel());
        listRoles.remove(0);
        Result<?> result = context.select().from(ROLE).fetch();
        for (Record r : result) {
            RoleModel roleModel =new RoleModel(r.getValue(ROLE.ID),r.getValue(ROLE.NAME));
            listRoles.add(roleModel);
        }
        return listRoles;
    }
    public ArrayList<UserModel> searchUserByPhone(String phone,ObservableList<UserModel> listUsers) {
        ArrayList<UserModel> listUserFound = new ArrayList<>();
        listUsers.forEach(userModel -> {
            if(userModel.getPhone().contains(phone)){
                listUserFound.add(userModel);
            }
        });
        return listUserFound;
    }

}
