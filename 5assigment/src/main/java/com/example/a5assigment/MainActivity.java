package com.example.a5assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private class obj {
        Date pvm;
        String nimi;
    }

    RequestQueue jono;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        jono = Volley.newRequestQueue(this);
        lista = new ArrayList<>();
        adapteri = new OmaAdapteri(getApplicationContext(), R.layout.listaitem, lista);
        listView.setAdapter(adapteri);
    }

    ListView listView;
    ArrayList<dada> lista;
    OmaAdapteri adapteri;

    @Override
    protected void onResume() {
        super.onResume();
        request = new JsonArrayRequest(
                "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Type listantyyppi = new TypeToken<ArrayList<dada>>(){}.getType();
                        ArrayList<dada> lista;
                        lista = gson.fromJson(response.toString(), listantyyppi);
                        adapteri.addAll(lista);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        jono.add(request);
    }
}
