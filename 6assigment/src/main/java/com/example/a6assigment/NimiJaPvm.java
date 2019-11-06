package com.example.a6assigment;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NimiJaPvm {
    private String name;
    private Date date;
    private String dateDisplay;

    public NimiJaPvm(String name, String date) {
        this.name = name;
        setDate(date);
    }

    String getName() {
        return name;
    }

    void setName(String s) {
        name = s;
    }

    Date getDate() {
        return date;
    }

    String getDateDisplay() {
        return dateDisplay;
    }

    void setDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy HH:mm:ss");
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            Log.i("OMA", "setDate: parse errori " + s);
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatDisplay = new SimpleDateFormat("dd.MM.yyyy");
        dateDisplay = dateFormatDisplay.format(date);
    }
}
