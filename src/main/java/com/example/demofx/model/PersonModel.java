package com.example.demofx.model;

public class PersonModel  {


    private  long id;
    private String fName;
    private String lName;

    public PersonModel() {
    }

    public PersonModel(long id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return getfName()+" "+getlName();
    }
}
