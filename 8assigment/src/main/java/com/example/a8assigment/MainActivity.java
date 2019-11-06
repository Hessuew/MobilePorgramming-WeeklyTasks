package com.example.a8assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager cm;
    private Context context;
    private RequestQueue queue = null;
    private Button searchButton;
    private EditText searchText;
    private ownAdapter adapter;
    private MinunTaulu minunTaulu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ownAdapter(this, R.layout.listtemplate, minunTaulu);
        ListView listview = findViewById(R.id.listView);
        listview.setAdapter(adapter);

        searchButton = new Button(this);
        searchText = new EditText(this);

        searchButton.setText("Get Picture");
        searchText.setHint("Search");

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(searchText.getText().toString().trim().length() == 0) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }else {

                    doJsonQuery(searchText.getText().toString());
                }
            }
        });

        LinearLayout toolb = findViewById(R.id.toolbar);
        toolb.setBackgroundColor(Color.GRAY);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
        searchText.setBackgroundColor(Color.WHITE);
        toolb.addView(searchText, textParams);
        toolb.addView(searchButton, buttonParams);

        if (context == null) {
            context = getApplicationContext();
        }

        if(!testInternet(context)) {
            Toast.makeText(context, "No network available.", Toast.LENGTH_SHORT).show();
        }
    }

    private void doJsonQuery(String tag) {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }

        String url = "https://loremflickr.com/json/g/320/240/"+tag+"/all";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            getDataFromResponse(response);
                        } catch (JSONException e) {
                            searchText.setText(e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void getDataFromResponse (JSONObject response) throws JSONException {

        Gson gson = new Gson();
        Type listantyyppi = new TypeToken<ArrayList<MinunTaulu>>(){}.getType();
        ArrayList<MinunTaulu> lista;

        lista = gson.fromJson(response.toString(), listantyyppi);

        RoomDatabase.Builder<Tietokanta> rakentaja = Room.databaseBuilder(getApplicationContext(), Tietokanta.class, (String) Tietokanta.NIMI);
        File file = new File("path");

        Tietokanta kanta = rakentaja.build();

        MinunTauluDao dataaccessobject = kanta.MinunTauluDao();
        MinunTaulu x = new MinunTaulu();
        x.url = (String) response.get("file");
        x.license = (String) response.get("license");
        x.owner = (String) response.get("owner");
        x.width = (int) response.get("width");
        x.height = (int) response.get("height");
        x.filter = (String) response.get("filter");
        x.tags = (String) response.get("tags");
        x.tagMode = (String) response.get("tagMode");
        dataaccessobject.InsertMyEntity(x);

        dataaccessobject.getAllInDescendingOrder();
        adapter.notifyDataSetChanged();

        Toast.makeText(context, "Data loaded", Toast.LENGTH_SHORT).show();
    }

    private boolean testInternet(Context context) {

        final Network[] allNetworks;
        cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        allNetworks = cm.getAllNetworks();
        return (allNetworks != null);
    }
}

