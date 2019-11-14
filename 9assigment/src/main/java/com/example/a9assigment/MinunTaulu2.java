package com.example.a9assigment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MinunTaulu2 {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String Username;
    public boolean successfull;
    public String timestamp;
}
