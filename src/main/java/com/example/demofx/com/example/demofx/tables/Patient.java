/*
 * This file is generated by jOOQ.
 */
package com.example.demofx.tables;


import com.example.demofx.Doctorlite;
import com.example.demofx.Keys;
import com.example.demofx.tables.records.PatientRecord;

import java.time.LocalDate;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function14;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Patient extends TableImpl<PatientRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>doctorlite.patient</code>
     */
    public static final Patient PATIENT = new Patient();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PatientRecord> getRecordType() {
        return PatientRecord.class;
    }

    /**
     * The column <code>doctorlite.patient.id</code>.
     */
    public final TableField<PatientRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>doctorlite.patient.firstName</code>.
     */
    public final TableField<PatientRecord, String> FIRSTNAME = createField(DSL.name("firstName"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>doctorlite.patient.lastName</code>.
     */
    public final TableField<PatientRecord, String> LASTNAME = createField(DSL.name("lastName"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>doctorlite.patient.phone</code>.
     */
    public final TableField<PatientRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>doctorlite.patient.address</code>.
     */
    public final TableField<PatientRecord, String> ADDRESS = createField(DSL.name("address"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.patient.civilStatus</code>.
     */
    public final TableField<PatientRecord, String> CIVILSTATUS = createField(DSL.name("civilStatus"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.patient.worke</code>.
     */
    public final TableField<PatientRecord, String> WORKE = createField(DSL.name("worke"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.patient.scientificLevel</code>.
     */
    public final TableField<PatientRecord, String> SCIENTIFICLEVEL = createField(DSL.name("scientificLevel"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.patient.socioEconomicLevel</code>.
     */
    public final TableField<PatientRecord, String> SOCIOECONOMICLEVEL = createField(DSL.name("socioEconomicLevel"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.patient.gender</code>.
     */
    public final TableField<PatientRecord, String> GENDER = createField(DSL.name("gender"), SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>doctorlite.patient.age</code>.
     */
    public final TableField<PatientRecord, Integer> AGE = createField(DSL.name("age"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>doctorlite.patient.birthday</code>.
     */
    public final TableField<PatientRecord, LocalDate> BIRTHDAY = createField(DSL.name("birthday"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>doctorlite.patient.height</code>.
     */
    public final TableField<PatientRecord, Integer> HEIGHT = createField(DSL.name("height"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>doctorlite.patient.wieght</code>.
     */
    public final TableField<PatientRecord, Double> WIEGHT = createField(DSL.name("wieght"), SQLDataType.FLOAT, this, "");

    private Patient(Name alias, Table<PatientRecord> aliased) {
        this(alias, aliased, null);
    }

    private Patient(Name alias, Table<PatientRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>doctorlite.patient</code> table reference
     */
    public Patient(String alias) {
        this(DSL.name(alias), PATIENT);
    }

    /**
     * Create an aliased <code>doctorlite.patient</code> table reference
     */
    public Patient(Name alias) {
        this(alias, PATIENT);
    }

    /**
     * Create a <code>doctorlite.patient</code> table reference
     */
    public Patient() {
        this(DSL.name("patient"), null);
    }

    public <O extends Record> Patient(Table<O> child, ForeignKey<O, PatientRecord> key) {
        super(child, key, PATIENT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Doctorlite.DOCTORLITE;
    }

    @Override
    public Identity<PatientRecord, Long> getIdentity() {
        return (Identity<PatientRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PatientRecord> getPrimaryKey() {
        return Keys.KEY_PATIENT_PRIMARY;
    }

    @Override
    public Patient as(String alias) {
        return new Patient(DSL.name(alias), this);
    }

    @Override
    public Patient as(Name alias) {
        return new Patient(alias, this);
    }

    @Override
    public Patient as(Table<?> alias) {
        return new Patient(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Patient rename(String name) {
        return new Patient(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Patient rename(Name name) {
        return new Patient(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Patient rename(Table<?> name) {
        return new Patient(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<Long, String, String, String, String, String, String, String, String, String, Integer, LocalDate, Integer, Double> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function14<? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Integer, ? super LocalDate, ? super Integer, ? super Double, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function14<? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Integer, ? super LocalDate, ? super Integer, ? super Double, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}