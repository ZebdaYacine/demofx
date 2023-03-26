package com.example.demofx.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import net.synedra.validatorfx.Validator;

public class ValidationInputs {

    private Validator validator ;


    public ValidationInputs() {
        this.validator = new Validator();
    }

    public void validateTextField(MFXTextField text,String key){
        validator.createCheck()
                .dependsOn(key, text.textProperty())
                .withMethod(c -> {
                    String userName = c.get(key);
                    if (userName.isEmpty()) {
                        text.getStyleClass().add("-fx-background-color: #b61515");
                    }
                })
                .decorates(text)
                .immediate();
    }
}
