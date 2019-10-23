package com.example.a6assigment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

class Tietokanta extends SQLiteOpenHelper {
    private ArrayList<dada> results = new ArrayList<dada>();
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nimi";
    private static final String KEY_LOC = "aika";
    public Tietokanta(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    public Tietokanta(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    void insertUserDetails(String [] item){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        for(int i=0;i<item.length;i+=2){
            cValues.put(KEY_NAME, item[i]);
            cValues.put(KEY_NAME, item[i+1]);
            db.insert(TABLE_Users, null, cValues);
        }
        db.close();
    }
    public ArrayList<dada> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase sampleDB = this.getReadableDatabase();

        Cursor c = sampleDB.rawQuery("SELECT nimi,aika FROM " +
                DB_NAME, null);
        if (c != null ) {
            if (c.moveToFirst())
                do {
                    dada results1 = new dada();
                    String dbnimi = c.getString(c.getColumnIndex("nimi"));
                    String dbaika = c.getString(c.getColumnIndex("aika"));
                    results1.nimi = dbnimi;
                    results1.pvm = dbaika;
                    results.add(results1);
                } while (c.moveToNext());
        }
        db.close();
        return results;
    }
}
