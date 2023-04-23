/*
 * This file is generated by jOOQ.
 */
package com.example.demofx.databaseManger.jooq.tables;


import com.example.demofx.databaseManger.jooq.Doctorlite;
import com.example.demofx.databaseManger.jooq.Indexes;
import com.example.demofx.databaseManger.jooq.Keys;
import com.example.demofx.databaseManger.jooq.tables.records.DiagnosticRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function13;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row13;
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
public class Diagnostic extends TableImpl<DiagnosticRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>doctorlite.diagnostic</code>
     */
    public static final Diagnostic DIAGNOSTIC = new Diagnostic();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DiagnosticRecord> getRecordType() {
        return DiagnosticRecord.class;
    }

    /**
     * The column <code>doctorlite.diagnostic.id</code>.
     */
    public final TableField<DiagnosticRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>doctorlite.diagnostic.dateDiagnostic</code>.
     */
    public final TableField<DiagnosticRecord, LocalDate> DATEDIAGNOSTIC = createField(DSL.name("dateDiagnostic"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>doctorlite.diagnostic.sickness</code>.
     */
    public final TableField<DiagnosticRecord, String> SICKNESS = createField(DSL.name("sickness"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.diagnostic.idPatient</code>.
     */
    public final TableField<DiagnosticRecord, Long> IDPATIENT = createField(DSL.name("idPatient"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>doctorlite.diagnostic.idFollow</code>.
     */
    public final TableField<DiagnosticRecord, Long> IDFOLLOW = createField(DSL.name("idFollow"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>doctorlite.diagnostic.mediclaDiagnostic</code>.
     */
    public final TableField<DiagnosticRecord, String> MEDICLADIAGNOSTIC = createField(DSL.name("mediclaDiagnostic"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>doctorlite.diagnostic.psychologyDiagnostic</code>.
     */
    public final TableField<DiagnosticRecord, String> PSYCHOLOGYDIAGNOSTIC = createField(DSL.name("psychologyDiagnostic"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>doctorlite.diagnostic.interviewDynamics</code>.
     */
    public final TableField<DiagnosticRecord, String> INTERVIEWDYNAMICS = createField(DSL.name("interviewDynamics"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>doctorlite.diagnostic.conclusion</code>.
     */
    public final TableField<DiagnosticRecord, String> CONCLUSION = createField(DSL.name("conclusion"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>doctorlite.diagnostic.recipePsuchologist</code>.
     */
    public final TableField<DiagnosticRecord, String> RECIPEPSUCHOLOGIST = createField(DSL.name("recipePsuchologist"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>doctorlite.diagnostic.recipeMedicale</code>.
     */
    public final TableField<DiagnosticRecord, String> RECIPEMEDICALE = createField(DSL.name("recipeMedicale"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>doctorlite.diagnostic.idDoctor</code>.
     */
    public final TableField<DiagnosticRecord, Long> IDDOCTOR = createField(DSL.name("idDoctor"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>doctorlite.diagnostic.idPsychologist</code>.
     */
    public final TableField<DiagnosticRecord, Long> IDPSYCHOLOGIST = createField(DSL.name("idPsychologist"), SQLDataType.BIGINT, this, "");

    private Diagnostic(Name alias, Table<DiagnosticRecord> aliased) {
        this(alias, aliased, null);
    }

    private Diagnostic(Name alias, Table<DiagnosticRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>doctorlite.diagnostic</code> table reference
     */
    public Diagnostic(String alias) {
        this(DSL.name(alias), DIAGNOSTIC);
    }

    /**
     * Create an aliased <code>doctorlite.diagnostic</code> table reference
     */
    public Diagnostic(Name alias) {
        this(alias, DIAGNOSTIC);
    }

    /**
     * Create a <code>doctorlite.diagnostic</code> table reference
     */
    public Diagnostic() {
        this(DSL.name("diagnostic"), null);
    }

    public <O extends Record> Diagnostic(Table<O> child, ForeignKey<O, DiagnosticRecord> key) {
        super(child, key, DIAGNOSTIC);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Doctorlite.DOCTORLITE;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.DIAGNOSTIC_IDDOCTOR, Indexes.DIAGNOSTIC_IDFOLLOW, Indexes.DIAGNOSTIC_IDPATIENT, Indexes.DIAGNOSTIC_IDPSYCHOLOGIST);
    }

    @Override
    public Identity<DiagnosticRecord, Long> getIdentity() {
        return (Identity<DiagnosticRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<DiagnosticRecord> getPrimaryKey() {
        return Keys.KEY_DIAGNOSTIC_PRIMARY;
    }

    @Override
    public List<ForeignKey<DiagnosticRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DIAGNOSTIC_IBFK_1, Keys.DIAGNOSTIC_IBFK_2, Keys.DIAGNOSTIC_IBFK_3, Keys.DIAGNOSTIC_IBFK_4);
    }

    private transient Patient _patient;
    private transient Follow _follow;
    private transient User _diagnosticIbfk_3;
    private transient User _diagnosticIbfk_4;

    /**
     * Get the implicit join path to the <code>doctorlite.patient</code> table.
     */
    public Patient patient() {
        if (_patient == null)
            _patient = new Patient(this, Keys.DIAGNOSTIC_IBFK_1);

        return _patient;
    }

    /**
     * Get the implicit join path to the <code>doctorlite.follow</code> table.
     */
    public Follow follow() {
        if (_follow == null)
            _follow = new Follow(this, Keys.DIAGNOSTIC_IBFK_2);

        return _follow;
    }

    /**
     * Get the implicit join path to the <code>doctorlite.user</code> table, via
     * the <code>diagnostic_ibfk_3</code> key.
     */
    public User diagnosticIbfk_3() {
        if (_diagnosticIbfk_3 == null)
            _diagnosticIbfk_3 = new User(this, Keys.DIAGNOSTIC_IBFK_3);

        return _diagnosticIbfk_3;
    }

    /**
     * Get the implicit join path to the <code>doctorlite.user</code> table, via
     * the <code>diagnostic_ibfk_4</code> key.
     */
    public User diagnosticIbfk_4() {
        if (_diagnosticIbfk_4 == null)
            _diagnosticIbfk_4 = new User(this, Keys.DIAGNOSTIC_IBFK_4);

        return _diagnosticIbfk_4;
    }

    @Override
    public Diagnostic as(String alias) {
        return new Diagnostic(DSL.name(alias), this);
    }

    @Override
    public Diagnostic as(Name alias) {
        return new Diagnostic(alias, this);
    }

    @Override
    public Diagnostic as(Table<?> alias) {
        return new Diagnostic(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Diagnostic rename(String name) {
        return new Diagnostic(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Diagnostic rename(Name name) {
        return new Diagnostic(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Diagnostic rename(Table<?> name) {
        return new Diagnostic(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row13<Long, LocalDate, String, Long, Long, String, String, String, String, String, String, Long, Long> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function13<? super Long, ? super LocalDate, ? super String, ? super Long, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function13<? super Long, ? super LocalDate, ? super String, ? super Long, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
