package com.example.a9assigment.room;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// "username" is given a unique index so the username has to be unique.
@Entity(indices = {@Index(value = "username", unique = true)})
public class EntityUser {
    @PrimaryKey(autoGenerate = true)
    int id_user;
    public String username;
    public String password;
}
