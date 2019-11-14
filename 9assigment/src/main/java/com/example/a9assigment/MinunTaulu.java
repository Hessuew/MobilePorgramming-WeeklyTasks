package com.example.a9assigment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MinunTaulu {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;
    public String password;
}
