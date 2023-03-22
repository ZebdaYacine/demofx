/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demofx.databaseManger;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Zed Yacine
 */
public class DataBaseConnection {

    public static Connection con;

    public static String url="jdbc:mysql://localhost:3306/SchoolManager";
    //public static String url="jdbc:sqlite:db";
    public static String user="root";
    public static String Password="root";



    public static Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,Password);
            System.out.println("Connection with Data Base");

        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
            System.err.println("No Connection with Data Base");
        }
        return con;
    }

}
