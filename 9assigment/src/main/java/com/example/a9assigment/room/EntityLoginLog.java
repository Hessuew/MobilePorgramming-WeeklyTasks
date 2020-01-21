package com.example.a9assigment.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "login_log")
public class EntityLoginLog {
    @PrimaryKey(autoGenerate = true)
    int id_login_log;
    String username;
    Date timestamp;
    Boolean success;
}

