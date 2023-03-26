package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

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

    public void Template() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(this);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServiceModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
