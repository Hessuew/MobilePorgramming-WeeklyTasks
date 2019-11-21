package com.example.a10assigment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MinunTaulu {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String aukiVaiKiinni;
    public String time;
}
