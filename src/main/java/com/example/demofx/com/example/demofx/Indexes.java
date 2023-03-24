/*
 * This file is generated by jOOQ.
 */
package com.example.demofx;


import com.example.demofx.tables.Diagnostic;
import com.example.demofx.tables.Follow;
import com.example.demofx.tables.User;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in doctorlite.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index FOLLOW_IDDOCTOR = Internal.createIndex(DSL.name("idDoctor"), Follow.FOLLOW, new OrderField[] { Follow.FOLLOW.IDDOCTOR }, false);
    public static final Index DIAGNOSTIC_IDFOLLOW = Internal.createIndex(DSL.name("idFollow"), Diagnostic.DIAGNOSTIC, new OrderField[] { Diagnostic.DIAGNOSTIC.IDFOLLOW }, false);
    public static final Index DIAGNOSTIC_IDPATIENT = Internal.createIndex(DSL.name("idPatient"), Diagnostic.DIAGNOSTIC, new OrderField[] { Diagnostic.DIAGNOSTIC.IDPATIENT }, false);
    public static final Index FOLLOW_IDPATIENT = Internal.createIndex(DSL.name("idPatient"), Follow.FOLLOW, new OrderField[] { Follow.FOLLOW.IDPATIENT }, false);
    public static final Index FOLLOW_IDPSYCHOLOGIST = Internal.createIndex(DSL.name("idPsychologist"), Follow.FOLLOW, new OrderField[] { Follow.FOLLOW.IDPSYCHOLOGIST }, false);
    public static final Index USER_IDROLE = Internal.createIndex(DSL.name("idRole"), User.USER, new OrderField[] { User.USER.IDROLE }, false);
    public static final Index FOLLOW_IDSERVICE = Internal.createIndex(DSL.name("idService"), Follow.FOLLOW, new OrderField[] { Follow.FOLLOW.IDSERVICE }, false);
    public static final Index USER_IDSERVICE = Internal.createIndex(DSL.name("idService"), User.USER, new OrderField[] { User.USER.IDSERVICE }, false);
    public static final Index USER_IDTYPE = Internal.createIndex(DSL.name("idType"), User.USER, new OrderField[] { User.USER.IDTYPE }, false);
}
