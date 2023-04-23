package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.Patient;
import com.example.demofx.databaseManger.jooq.tables.records.DiagnosticRecord;
import com.example.demofx.databaseManger.jooq.tables.records.FollowRecord;
import com.example.demofx.databaseManger.jooq.tables.records.PatientRecord;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.Record;
import org.jooq.Result;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.*;

public class DiagnosticModel extends DiagnosticRecord {

    public DiagnosticModel() {
    }

    public DiagnosticModel(Long id, LocalDate datediagnostic, String sickness, Long idpatient, Long idfollow, String medicladiagnostic, String psychologydiagnostic, String interviewdynamics
            , String conclusion, String recipepsuchologist, String recipemedicale, Long iddoctor, Long idpsychologist) {
        super(id, datediagnostic, sickness, idpatient, idfollow, medicladiagnostic, psychologydiagnostic, interviewdynamics, conclusion, recipepsuchologist, recipemedicale, iddoctor, idpsychologist);
    }

    public PatientRecord getPatient() {
        PatientRecord patient = (PatientRecord) context.selectFrom(Patient.PATIENT).where(Patient.PATIENT.ID.eq(this.getIdpatient())).fetchOne();
        return (patient == null ? new PatientRecord() : patient);
    }

    public static Record getPatient(long id) {
        Record patient =  context.select().from(PATIENT).join(FOLLOW).on(PATIENT.ID.eq(FOLLOW.IDPATIENT))
                .where(FOLLOW.ID.eq(id)).fetchOne();
        return (patient == null ? new PatientRecord() : patient);
    }


    public String getPatientFullName() {
        PatientRecord patient = getPatient();
        return (patient == null ? "" : patient.getFirstname() + " " + patient.getLastname());
    }

    public UserRecord getPs() {
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIdpsychologist())).fetchOne();
        return (user == null ? new UserModel() : user);
    }

    public String getPsFullName() {
        UserRecord user = getPs();
        return (user == null ? "" : user.getFirstname() + " " + user.getLastname());
    }

    public UserRecord getDr() {
        UserRecord user = (UserRecord) context.selectFrom(USER).where(USER.ID.eq(this.getIddoctor())).fetchOne();
        return (user == null ? new UserModel() : user);
    }

    public String getDrFullName() {
        UserRecord user = getDr();
        return (user == null ? "" : user.getFirstname() + " " + user.getLastname());
    }

    public FollowRecord getFollowIdList() {
        FollowRecord followRecord = (FollowRecord) context.selectFrom(FOLLOW).fetch();
        return (followRecord == null ? new FollowRecord() : followRecord);
    }

    public FollowRecord getFollowIdList(long idPatient) {
        FollowRecord followRecord = (FollowRecord) context.selectFrom(FOLLOW).where(FOLLOW.IDPATIENT.eq(idPatient)).fetchOne();
        return (followRecord == null ? new FollowRecord() : followRecord);
    }

    public String getDateDiagnosticToString() {
        return getDatediagnostic() != null ? getDatediagnostic().toString() : "";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<DiagnosticModel> getAllDiagnostic() {
        Result<?> result = context.select().from(DIAGNOSTIC)
                .fetch();
        ArrayList<DiagnosticModel> listDiagnostic = new ArrayList<>();
        for (Record r : result) {
            DiagnosticModel diagnosticModel = new DiagnosticModel();
            diagnosticModel.setId(r.getValue(DIAGNOSTIC.ID));
            diagnosticModel.setIdpatient(r.getValue(DIAGNOSTIC.IDPATIENT));
            diagnosticModel.setIddoctor(r.getValue(DIAGNOSTIC.IDDOCTOR));
            diagnosticModel.setIdpsychologist(r.getValue(DIAGNOSTIC.IDPSYCHOLOGIST));
            diagnosticModel.setDatediagnostic(r.getValue(DIAGNOSTIC.DATEDIAGNOSTIC));
            listDiagnostic.add(diagnosticModel);
        }
        return listDiagnostic;
    }

    public static ObservableList<PatientModel> fetchPatients(long idFollow) {
        ObservableList<PatientModel> listPatients = FXCollections.observableArrayList(new PatientModel());
        listPatients.remove(0);
        PatientModel patientModel = new PatientModel(getPatient(idFollow).getValue(PATIENT.ID), getPatient(idFollow).getValue(PATIENT.LASTNAME)
                , getPatient(idFollow).getValue(PATIENT.FIRSTNAME));
        listPatients.add(patientModel);
        return listPatients;
    }


}
