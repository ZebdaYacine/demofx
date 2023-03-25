package com.example.demofx.controller;

import com.example.demofx.databaseManger.DataBaseConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import static com.example.demofx.databaseManger.DataBaseConnection.con;
class UsersControllerTest {

    @Test
    void getAllUsers() {
        DataBaseConnection.Connect();
        DSLContext context = DSL.using(con, SQLDialect.MYSQL);
        UsersController usersController = new UsersController();
        usersController.getAllUsers(context).forEach(userModel -> {
            System.out.println(userModel.getRole());
        });
    }
}