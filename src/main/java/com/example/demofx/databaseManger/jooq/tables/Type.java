/*
 * This file is generated by jOOQ.
 */
package com.example.demofx.databaseManger.jooq.tables;


import com.example.demofx.databaseManger.jooq.Doctorlite;
import com.example.demofx.databaseManger.jooq.Keys;
import com.example.demofx.databaseManger.jooq.tables.records.TypeRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
public class Type extends TableImpl<TypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>doctorlite.type</code>
     */
    public static final Type TYPE = new Type();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TypeRecord> getRecordType() {
        return TypeRecord.class;
    }

    /**
     * The column <code>doctorlite.type.id</code>.
     */
    public final TableField<TypeRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>doctorlite.type.name</code>.
     */
    public final TableField<TypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(20), this, "");

    private Type(Name alias, Table<TypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Type(Name alias, Table<TypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>doctorlite.type</code> table reference
     */
    public Type(String alias) {
        this(DSL.name(alias), TYPE);
    }

    /**
     * Create an aliased <code>doctorlite.type</code> table reference
     */
    public Type(Name alias) {
        this(alias, TYPE);
    }

    /**
     * Create a <code>doctorlite.type</code> table reference
     */
    public Type() {
        this(DSL.name("type"), null);
    }

    public <O extends Record> Type(Table<O> child, ForeignKey<O, TypeRecord> key) {
        super(child, key, TYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Doctorlite.DOCTORLITE;
    }

    @Override
    public Identity<TypeRecord, Long> getIdentity() {
        return (Identity<TypeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<TypeRecord> getPrimaryKey() {
        return Keys.KEY_TYPE_PRIMARY;
    }

    @Override
    public Type as(String alias) {
        return new Type(DSL.name(alias), this);
    }

    @Override
    public Type as(Name alias) {
        return new Type(alias, this);
    }

    @Override
    public Type as(Table<?> alias) {
        return new Type(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Type rename(String name) {
        return new Type(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Type rename(Name name) {
        return new Type(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Type rename(Table<?> name) {
        return new Type(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
