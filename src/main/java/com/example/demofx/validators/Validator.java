package com.example.demofx.validators;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class Validator {

    @FXML
    private MFXTextField Fname, Lname, phone, address,height,weight,work;

    @FXML
    private MFXComboBox<String> genderCmbox,civilStatusCmbox;

    @FXML
    private MFXDatePicker birthday;


    public Validator(MFXTextField fname, MFXTextField lname, MFXTextField phone, MFXTextField address, MFXTextField height
            , MFXTextField weight, MFXTextField work, MFXComboBox<String> genderCmbox, MFXComboBox<String> civilStatusCmbox, MFXDatePicker birthday) {
        Fname = fname;
        Lname = lname;
        this.phone = phone;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.work = work;
        this.genderCmbox = genderCmbox;
        this.civilStatusCmbox = civilStatusCmbox;
        this.birthday = birthday;
    }

    public MFXTextField getFname() {
        return Fname;
    }

    public void setFname(MFXTextField fname) {
        Fname = fname;
    }

    public MFXTextField getLname() {
        return Lname;
    }

    public void setLname(MFXTextField lname) {
        Lname = lname;
    }

    public MFXTextField getPhone() {
        return phone;
    }

    public void setPhone(MFXTextField phone) {
        this.phone = phone;
    }

    public MFXTextField getAddress() {
        return address;
    }

    public void setAddress(MFXTextField address) {
        this.address = address;
    }

    public MFXTextField getHeight() {
        return height;
    }

    public void setHeight(MFXTextField height) {
        this.height = height;
    }

    public MFXTextField getWeight() {
        return weight;
    }

    public void setWeight(MFXTextField weight) {
        this.weight = weight;
    }

    public MFXTextField getWork() {
        return work;
    }

    public void setWork(MFXTextField work) {
        this.work = work;
    }

    public MFXComboBox<String> getGenderCmbox() {
        return genderCmbox;
    }

    public void setGenderCmbox(MFXComboBox<String> genderCmbox) {
        this.genderCmbox = genderCmbox;
    }

    public MFXComboBox<String> getCivilStatusCmbox() {
        return civilStatusCmbox;
    }

    public void setCivilStatusCmbox(MFXComboBox<String> civilStatusCmbox) {
        this.civilStatusCmbox = civilStatusCmbox;
    }

    public MFXDatePicker getBirthday() {
        return birthday;
    }

    public void setBirthday(MFXDatePicker birthday) {
        this.birthday = birthday;
    }
}
