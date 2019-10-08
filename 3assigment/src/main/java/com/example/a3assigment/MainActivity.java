package com.example.a3assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Date> lista = new ArrayList<>();
    private omaAdapteri adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Add custom adapter to the ListView.
        adapter = new omaAdapteri(this, R.layout.listatemplate, R.id.textView, lista);
        ListView listview = findViewById(R.id.listView);
        listview.setAdapter(adapter);

        // Button above the ListView.
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date aika = Calendar.getInstance().getTime();
                lista.add(aika);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
