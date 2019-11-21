package com.example.a10assigment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MinunTaulu.class}, version = 1)
public abstract class Tietokanta extends RoomDatabase {
    public static final String NIMI = "TIETOKANTA";
    public abstract MinunTauluDao MinunTauluDao();
}
