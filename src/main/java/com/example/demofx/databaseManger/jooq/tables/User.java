/*
 * This file is generated by jOOQ.
 */
package com.example.demofx.databaseManger.jooq.tables;


import com.example.demofx.databaseManger.jooq.Doctorlite;
import com.example.demofx.databaseManger.jooq.Indexes;
import com.example.demofx.databaseManger.jooq.Keys;
import com.example.demofx.databaseManger.jooq.tables.records.UserRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>doctorlite.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>doctorlite.user.id</code>.
     */
    public final TableField<UserRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>doctorlite.user.userName</code>.
     */
    public final TableField<UserRecord, String> USERNAME = createField(DSL.name("userName"), SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>doctorlite.user.password</code>.
     */
    public final TableField<UserRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>doctorlite.user.firstName</code>.
     */
    public final TableField<UserRecord, String> FIRSTNAME = createField(DSL.name("firstName"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>doctorlite.user.lastName</code>.
     */
    public final TableField<UserRecord, String> LASTNAME = createField(DSL.name("lastName"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>doctorlite.user.phone</code>.
     */
    public final TableField<UserRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>doctorlite.user.type</code>.
     */
    public final TableField<UserRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>doctorlite.user.idService</code>.
     */
    public final TableField<UserRecord, Long> IDSERVICE = createField(DSL.name("idService"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>doctorlite.user.idRole</code>.
     */
    public final TableField<UserRecord, Long> IDROLE = createField(DSL.name("idRole"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>doctorlite.user.idType</code>.
     */
    public final TableField<UserRecord, Long> IDTYPE = createField(DSL.name("idType"), SQLDataType.BIGINT.nullable(false), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>doctorlite.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>doctorlite.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>doctorlite.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    public <O extends Record> User(Table<O> child, ForeignKey<O, UserRecord> key) {
        super(child, key, USER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Doctorlite.DOCTORLITE;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.USER_IDROLE, Indexes.USER_IDSERVICE, Indexes.USER_IDTYPE);
    }

    @Override
    public Identity<UserRecord, Long> getIdentity() {
        return (Identity<UserRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    @Override
    public List<ForeignKey<UserRecord, ?>> getReferences() {
        return Arrays.asList(Keys.USER_IBFK_3, Keys.USER_IBFK_2, Keys.USER_IBFK_1);
    }

    private transient Service _service;
    private transient Role _role;
    private transient Type _type;

    /**
     * Get the implicit join path to the <code>doctorlite.service</code> table.
     */
    public Service service() {
        if (_service == null)
            _service = new Service(this, Keys.USER_IBFK_3);

        return _service;
    }

    /**
     * Get the implicit join path to the <code>doctorlite.role</code> table.
     */
    public Role role() {
        if (_role == null)
            _role = new Role(this, Keys.USER_IBFK_2);

        return _role;
    }

    /**
     * Get the implicit join path to the <code>doctorlite.type</code> table.
     */
    public Type type() {
        if (_type == null)
            _type = new Type(this, Keys.USER_IBFK_1);

        return _type;
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    @Override
    public User as(Table<?> alias) {
        return new User(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Table<?> name) {
        return new User(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, String, String, String, String, String, String, Long, Long, Long> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function10<? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function10<? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
