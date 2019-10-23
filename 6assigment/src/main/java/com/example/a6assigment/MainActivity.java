package com.example.a6assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Fragment1.IsisaltoFragment, Fragment2.ShowData{

    Tietokanta tietokanta = new Tietokanta(MainActivity.this);
    RequestQueue jono;
    JsonArrayRequest request;
    Object obj = getSupportFragmentManager().findFragmentById(R.id.fragment2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jono = Volley.newRequestQueue(this);
        OmaAdapteri = new OmaAdapteri(getApplicationContext(), R.layout.listaitem, lista);
        listView.setAdapter(OmaAdapteri);
    }

    OmaAdapteri OmaAdapteri;
    ListView listView;
    public ArrayList<dada> lista;

    @Override
    public void Button1Pressed(Fragment1.IcallBack icallBack) {
        request = new JsonArrayRequest(
                "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Type listantyyppi = new TypeToken<ArrayList<dada>>(){}.getType();
                        lista = gson.fromJson(response.toString(), listantyyppi);
                        String[] item = lista.toArray(new String[lista.size()]);
                        tietokanta.insertUserDetails(item);
                        Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        jono.add(request);
    }

    @Override
    public void Button2Pressed(Fragment1.IcallBack icallBack) {
        lista = tietokanta.GetUsers();
    }

    @Override
    public void ShowDataonList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            OmaAdapteri.addAll(lista);
        }
    }
}
