package com.example.demofx.validators;

import com.example.demofx.model.PatientModel;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.lang.reflect.Field;

public class PatientValidator extends Validator{

    public PatientValidator(MFXTextField fname, MFXTextField lname, MFXTextField phone, MFXTextField address,
                            MFXTextField height, MFXTextField weight, MFXTextField work, MFXComboBox<String> genderCmbox,
                            MFXComboBox<String> civilStatusCmbox, MFXDatePicker birthday) {
        super(fname, lname, phone, address, height, weight, work, genderCmbox, civilStatusCmbox, birthday);
    }

    public PatientModel validatePatientInputs() throws IllegalAccessException {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.get(this) != null) {
                    System.out.println(field.get(this).toString());
                }
            }
        /*PatientModel patientModel = new PatientModel();
        boolean isValidate=false;
        if(patientValidator.getBirthday()!=null) {
            patientModel.setBirthday(patientValidator.getBirthday().getValue());
            patientValidator.getBirthday().setStyle("");
            isValidate=true;
        }else{
            patientValidator.getBirthday().setStyle("-fx-border-color: red");
        }
        patientModel.setValidate(isValidate);
        return  patientModel;*/
        return new PatientModel();
    }


}
