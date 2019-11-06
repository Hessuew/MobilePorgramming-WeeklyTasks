package com.example.a5assigment;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class dada {
    private Date pvm;
    private String nimi;

    public dada(){
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
