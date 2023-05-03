package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.PATIENT;

public class PatientModel extends PatientRecord {

    public PatientModel() {
    }

    public PatientModel(Long id, String firstname, String lastname) {
        this.setId(id);
        this.setFirstname(firstname);
        this.setLastname(lastname);
    }

    public String getBirthdayString() {
        return this.getBirthday().toString() ;
    }

    @Override
    public String toString() {
        return getFirstname()+" "+getLastname();
    }

    public static ArrayList<PatientModel> getAllPatients() {
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

    public static ObservableList<PatientModel> fetchPatients() {
        ObservableList<PatientModel> listPatients= FXCollections.observableArrayList(new PatientModel());
        listPatients.remove(0);
        Result<?> result = context.select().from(PATIENT).fetch();
        for (Record r : result) {
            PatientModel patientModel = new PatientModel(r.getValue(PATIENT.ID),r.getValue(PATIENT.LASTNAME),r.getValue(PATIENT.FIRSTNAME));
            listPatients.add(patientModel);
        }
        return listPatients;
    }

    public static PatientModel getPatientById(long id) {
        PatientModel patientModel=  new PatientModel();
        Result<?> result = context.select().from(PATIENT).where(PATIENT.ID.eq(id)).fetch();
        for (Record r : result) {
             patientModel = new PatientModel(r.getValue(PATIENT.ID),r.getValue(PATIENT.LASTNAME),r.getValue(PATIENT.FIRSTNAME));
        }
        return patientModel;
    }


}
