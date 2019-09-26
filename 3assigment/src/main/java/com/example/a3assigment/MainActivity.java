package com.example.a3assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    ListView simpleList;
    adapter listadapter;
    Button nappi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleList = findViewById(R.id.listaView);
        nappi = findViewById(R.id.add_btn);

        listadapter = new adapter(this, android.R.layout.simple_list_item_1);
        simpleList.setAdapter(listadapter);

        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listadapter.add(Calendar.getInstance().getTime());
            }

        });

    }
}
