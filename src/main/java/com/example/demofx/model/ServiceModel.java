package com.example.demofx.model;

import com.example.demofx.databaseManger.jooq.tables.records.ServiceRecord;
import org.jooq.Record;
import org.jooq.Result;

import static com.example.demofx.DemoFX.context;
import static com.example.demofx.databaseManger.jooq.Tables.FOLLOW;
import static com.example.demofx.databaseManger.jooq.Tables.SERVICE;

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

    public static ServiceModel getServiceById(long id) {
        ServiceModel serviceModel=  new ServiceModel();
        Result<?> result = context.select().from(SERVICE).where(SERVICE.ID.eq(id)).fetch();
        for (Record r : result) {
            serviceModel = new ServiceModel(r.getValue(SERVICE.ID),r.getValue(SERVICE.NAME));
        }
        return serviceModel;
    }

    public static ServiceModel getServiceByFollowId(long id) {
       ServiceModel serviceModel= new ServiceModel();
        Result<?> result = context.select().from(SERVICE).join(FOLLOW).on(FOLLOW.IDSERVICE.eq(SERVICE.ID))
                .where(FOLLOW.ID.eq(id))
                .fetch();
        for (Record r : result) {
            serviceModel = new ServiceModel(r.getValue(SERVICE.ID),r.getValue(SERVICE.NAME));
        }
        return serviceModel;
    }

}
