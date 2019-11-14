package com.example.a9assigment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MinunTaulu.class, MinunTaulu2.class}, version = 1)
public abstract class Tietokanta extends RoomDatabase {
    public static final String NIMI = "TIETOKANTA";
    public abstract MinunTauluDao MinunTauluDao();
}
