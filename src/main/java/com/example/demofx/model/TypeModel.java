package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.TypeRecord;

public class TypeModel extends TypeRecord {

    public TypeModel() {
    }

    public TypeModel(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return this.getName();
    }


}
