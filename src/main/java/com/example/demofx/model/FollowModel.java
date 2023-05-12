package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.Patient;
import com.example.demofx.databaseManger.jooq.tables.records.FollowRecord;
import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.Record;
import org.jooq.Result;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.*;
import static com.example.demofx.databaseManger.jooq.Tables.FOLLOW;

public class FollowModel extends FollowRecord {

    public FollowModel() {
    }

    public FollowModel(long id){
        this.setId(id);
    }
    public FollowModel(Long id, LocalDate dateenter, LocalDate datego, Long idpatient, Long idservice, Long iddoctor, Long idpsychologist, String sickness, String status) {
        super(id, dateenter, datego, idpatient, idservice, iddoctor, idpsychologist, sickness, status);
    }

    public PatientModel getPatient(){
        PatientModel patient = (PatientModel) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return  (patient==null?new PatientModel():patient);
    }

    public String getPatientAddress(){
        PatientRecord patient = (PatientRecord) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return  (patient!=null?patient.getAddress():"");
    }

    public String getBirthdayDate(){
        PatientRecord patient = (PatientRecord) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return  (patient!=null?patient.getBirthday().toString():"");
    }

    public String getPatientFullName(){
        PatientRecord patient = (PatientRecord) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return  (patient==null?"":patient.getFirstname()+" "+patient.getLastname());
    }

    public UserRecord getPs(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIdpsychologist())).fetchOne();
        return  (user==null?new UserModel():user);
    }
    public String getPsFullName(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIdpsychologist())).fetchOne();
        return  (user==null?"":user.getFirstname()+" "+user.getLastname());
    }
    public UserRecord getDr(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIddoctor())).fetchOne();
        return  (user==null?new UserModel():user);
    }
    public String getDrFullName(){
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIddoctor())).fetchOne();
        return  (user==null?"":user.getFirstname()+" "+user.getLastname());
    }
    public String getServiceName(){
        ServiceRecord service = (ServiceRecord) context.selectFrom(SERVICE).where(SERVICE.ID.eq(this.getIdservice())).fetchOne();
        return  (service==null?"":service.getName());
    }
    public ServiceRecord getService(){
        ServiceRecord service = (ServiceRecord) context.selectFrom(SERVICE).where(SERVICE.ID.eq(this.getIdservice())).fetchOne();
        return  (service==null?new ServiceModel():service);
    }
    public String getDateenterToString() {
        return getDateenter()!=null ? getDateenter().toString() :"" ;
    }
    public String getDategoToString() {
        return getDatego()!=null?getDatego().toString():"";
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    public ArrayList<FollowModel> getAllFollows() {
        Result<?> result = context.select().from(FOLLOW)
                .fetch();
        ArrayList<FollowModel> listUser = new ArrayList<>();
        for (Record r : result) {
            FollowModel followModel = new FollowModel();
            followModel.setId(r.getValue(FOLLOW.ID));
            followModel.setIdpatient(r.getValue(FOLLOW.IDPATIENT));
            followModel.setIddoctor(r.getValue(FOLLOW.IDDOCTOR));
            followModel.setIdservice(r.getValue(FOLLOW.IDSERVICE));
            followModel.setIdpsychologist(r.getValue(FOLLOW.IDPSYCHOLOGIST));
            followModel.setDatego(r.getValue(FOLLOW.DATEGO));
            followModel.setDateenter(r.getValue(FOLLOW.DATEENTER));
            followModel.setStatus(r.getValue(FOLLOW.STATUS));
            followModel.setSickness(r.getValue(FOLLOW.SICKNESS));
            listUser.add(followModel);
        }
        return listUser;
    }

    public static FollowModel getFollowId(FollowModel followModel) {
        ObservableList<FollowModel> listFollow= FXCollections.observableArrayList(new FollowModel());
        listFollow.remove(0);
        Result<?> result = context.select().from(FOLLOW)
                .where(FOLLOW.IDPATIENT.eq(followModel.getIdpatient()))
                .and(FOLLOW.DATEENTER.eq(followModel.getDateenter()))
                .and(FOLLOW.IDSERVICE.eq(followModel.getIdservice()))
                .fetch();
        for (Record r : result) {
            followModel.setId(r.getValue(FOLLOW.ID));
        }
        return followModel;
    }

    public static FollowModel getDateEnterByFollowId(long id) {
        FollowModel followModel= new FollowModel();
        Result<?> result = context.select().from(FOLLOW)
                .where(FOLLOW.ID.eq(id))
                .fetch();
        for (Record r : result) {
            followModel.setDateenter(r.getValue(FOLLOW.DATEENTER));
        }
        return followModel;
    }

    

}
