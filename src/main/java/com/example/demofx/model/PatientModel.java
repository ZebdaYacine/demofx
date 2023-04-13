package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;

import java.time.LocalDate;

public class PatientModel extends PatientRecord {

    private boolean isValidate;
    public PatientModel() {
    }

    public PatientModel(Long id, String firstname, String lastname, String phone, String address, String civilstatus, String worke, String scientificlevel, String socioeconomiclevel, String gender, Integer age, LocalDate birthday, Integer height, Double wieght) {
        super(id, firstname, lastname, phone, address, civilstatus, worke, scientificlevel, socioeconomiclevel, gender, age, birthday, height, wieght);
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}