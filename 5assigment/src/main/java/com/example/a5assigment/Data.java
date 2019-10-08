package com.example.a5assigment;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Data {
    private Date pvm;
    private String nimi;

    public Data(){
    }
    public Date getPvm() {
        return pvm;
    }

    public void setPvm(Date pvm) {
        this.pvm = pvm;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String name) {
        this.nimi = name;
    }
}
