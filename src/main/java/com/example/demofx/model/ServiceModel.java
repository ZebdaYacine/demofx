package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;

public class ServiceModel extends ServiceRecord {

    public ServiceModel() {
    }

    public ServiceModel(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return this.getName();
    }


}
