package com.example.a10assigment;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Date;
import java.util.Calendar;

public class IntentPalvelu extends IntentService {

    private Context context;

    public IntentPalvelu() {
        super("palvelunNimi");
    }
    public IntentPalvelu(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String currentTime = Calendar.getInstance().getTime().toString();
        String message = intent.getStringExtra("message");

        RoomDatabase.Builder<Tietokanta> rakentaja = Room.databaseBuilder(getApplicationContext(), Tietokanta.class, (String) Tietokanta.NIMI);

        final Tietokanta kanta = rakentaja.build();
        MinunTauluDao dataaccessobject = kanta.MinunTauluDao();
        MinunTaulu x = new MinunTaulu();
        x.aukiVaiKiinni = (message);
        x.time = (currentTime);

        dataaccessobject.InsertMyEntity(x);
    }
}
