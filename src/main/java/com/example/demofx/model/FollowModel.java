package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.FollowRecord;

import java.time.LocalDate;

public class FollowModel extends FollowRecord {

    public FollowModel() {
    }

    public FollowModel(Long id, LocalDate dateenter, LocalDate datego, Long idpatient, Long idservice, Long iddoctor, Long idpsychologist, String sickness, String status) {
        super(id, dateenter, datego, idpatient, idservice, iddoctor, idpsychologist, sickness, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
