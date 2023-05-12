package com.example.demofx.controller;

public class Utils {

    public static void trackingException(Exception e, String ErrorMessage, DialogsController dialogsController) {
        if (e != null){
            e.getStackTrace();
        }
        System.out.println(e.getMessage());
        dialogsController.openInfo(ErrorMessage);
    }

    public static boolean checkStringIsValid(String str) {
        if (str != null) {
            return !str.isBlank();
        }
        return false;
    }
}
