package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.RoleRecord;

public class RoleModel extends RoleRecord {

    public RoleModel() {
    }

    public RoleModel(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return this.getName();
    }


}
