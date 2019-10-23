package com.example.a6assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SisaltoFragment.IsisaltoFragment {

    Tietokanta tietokanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNappiaPainettu(SisaltoFragment.IcallBack icallBack){
        Toast.makeText(getApplicationContext(), "Nappia", Toast.LENGTH_LONG).show();
        icallBack.TakaisinKutsu(234);
    }
}
