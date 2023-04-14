package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.Patient;
import com.example.demofx.databaseManger.jooq.tables.records.FollowRecord;
import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;

import java.time.LocalDate;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.SERVICE;
import static com.example.demofx.databaseManger.jooq.Tables.USER;

public class FollowModel extends FollowRecord {

    public FollowModel() {
    }

    public FollowModel(Long id, LocalDate dateenter, LocalDate datego, Long idpatient, Long idservice, Long iddoctor, Long idpsychologist, String sickness, String status) {
        super(id, dateenter, datego, idpatient, idservice, iddoctor, idpsychologist, sickness, status);
    }

    public PatientModel getPatient(){
        PatientModel patient = (PatientModel) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return  patient;
    }

    public String getPatientFullName(){
        PatientRecord patient = (PatientRecord) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return  patient.getFirstname()+" "+patient.getLastname();
    }

    public UserRecord getPs(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIdpsychologist())).fetchOne();
        return  user;
    }
    public String getPsFullName(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIdpsychologist())).fetchOne();
        return  user.getFirstname()+" "+user.getLastname();
    }

    public UserRecord getDr(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIddoctor())).fetchOne();
        return  user;
    }
    public String getDrFullName(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIddoctor())).fetchOne();
        return  user.getFirstname()+" "+user.getLastname();
    }

    public String getServiceName(){
        ServiceRecord service = (ServiceRecord) context.selectFrom(SERVICE).where(SERVICE.ID.eq(this.getIdservice())).fetchOne();
        return  service.getName();
    }

    public ServiceRecord getService(){
        ServiceRecord service = (ServiceRecord) context.selectFrom(SERVICE).where(SERVICE.ID.eq(this.getIdservice())).fetchOne();
        return  service;
    }

    public String getDateenterToString() {
        return getDateenter().toString() ;
    }

    public String getDategoToString() {
        return getDatego().toString() ;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
