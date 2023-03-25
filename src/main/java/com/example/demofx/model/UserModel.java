package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;

public class UserModel extends UserRecord {

    private String service;
    private String role;

    public UserModel(Long id, String username, String password, String firstname, String lastname, String phone, String type, Long idservice, Long idrole, Long idtype, String service, String role) {
        super(id, username, password, firstname, lastname, phone, type, idservice, idrole, idtype);
        this.service = service;
        this.role = role;
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



}
