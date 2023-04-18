package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import org.jooq.Record;
import org.jooq.Result;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.PATIENT;

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

    public ArrayList<PatientModel> getAllPatients() {
        Result<?> result = context.select().from(PATIENT)
                .fetch();
        ArrayList<PatientModel> listUser = new ArrayList<>();
        for (Record r : result) {
            PatientModel patientRecord = new PatientModel();
            patientRecord.setAddress(r.getValue(PATIENT.ADDRESS));
            patientRecord.setLastname(r.getValue(PATIENT.LASTNAME));
            patientRecord.setFirstname(r.getValue(PATIENT.FIRSTNAME));
            patientRecord.setPhone(r.getValue(PATIENT.PHONE));
            patientRecord.setAge(r.getValue(PATIENT.AGE));
            patientRecord.setId(r.getValue(PATIENT.ID));
            patientRecord.setBirthday(r.getValue(PATIENT.BIRTHDAY));
            patientRecord.setWorke(r.getValue(PATIENT.WORKE));
            patientRecord.setGender(r.getValue(PATIENT.GENDER));
            patientRecord.setHeight(r.getValue(PATIENT.HEIGHT));
            patientRecord.setWieght(r.getValue(PATIENT.WIEGHT));
            patientRecord.setWorke(r.getValue(PATIENT.WORKE));
            patientRecord.setCivilstatus(r.getValue(PATIENT.CIVILSTATUS));
            listUser.add( patientRecord);
        }
        return listUser;
    }
}
