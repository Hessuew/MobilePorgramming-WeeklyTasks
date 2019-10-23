package com.example.a5assigment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RequestQueue jono;
    private ListView listView;
    private Button getbutton;
    private Gson gson;
    private omaAdapteri omaAdapteri;
    JsonArrayRequest request;

    private class obj {
        Date pvm;
        String nimi;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        jono = Volley.newRequestQueue(this);
        listView = findViewById(R.id.listView);
        getbutton = findViewById(R.id.button);
        getbutton.setOnClickListener(this);
        lista = new ArrayList<>();
        omaAdapteri = new omaAdapteri(getApplicationContext(), R.layout.listaitem, lista);
        listView.setAdapter(omaAdapteri);    }

    @Override
    public void onClick(View view) {
        final ArrayAdapter<String> myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(myAdapter);

        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            String message = "Internet yhteyden testaus onnistunui!";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        else {
            connected = false;
            String message = "Internet yhteyden testaus EI onnistunut!";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        if (connected == true) {
            jono = Volley.newRequestQueue(this);
            jsonParse();
        }
    }
    private void jsonParse(){

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
