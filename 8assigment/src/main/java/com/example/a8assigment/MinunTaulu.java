package com.example.a8assigment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MinunTaulu {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String url;
    public String license;
    public String owner;
    public int width;
    public int height;
    public String filter;
    public String tags;
    public String tagMode;
}
