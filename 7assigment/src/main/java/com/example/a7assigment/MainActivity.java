package com.example.a7assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue jono;
    private JsonArrayRequest request;
    private Toolbar t =(Toolbar)findViewById(R.id.toolbar);
    private ImageView mImageView;
    private String mImageURLString = " https://loremflickr.com/320/240/";
    private String mJSONURLString = "https://loremflickr.com/json/320/240/";
    private Button button;
    private EditText editText;
    private ImageAndTextAdapter ImageAndTextAdapter;
    private ListView listView;
    private ArrayList<String> LicenseAndOwner;
    private Bitmap response1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);

        button = new Button(this);
        editText = new EditText(this);
        button.setText("Push Me");
        editText.setText("Tags");
        //editText.setId(R.id.editText1);

        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        Toolbar.LayoutParams l2=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        button.setLayoutParams(l1);
        editText.setLayoutParams(l2);
        t.addView(button);
        t.addView(editText);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                call();
            }
        });
    }

    public void call(){
        mImageURLString = mImageURLString + editText.getText().toString();
        ImageRequest imageRequest = new ImageRequest(
                mImageURLString, // Image URL
                new Response.Listener<Bitmap>() { // Bitmap listener
                    @Override
                    public void onResponse(Bitmap response) {
                        // Do something with response

                        response1 = response;
                    }
                },
                0, // Image width
                0, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
                new Response.ErrorListener() { // Error listener
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something with error response
                        error.printStackTrace();
                    }
                }
        );
        jono.add(request);

        mJSONURLString = mJSONURLString + editText.getText().toString();
        request = new JsonArrayRequest(
                "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Type listantyyppi = new TypeToken<ArrayList<String>>(){}.getType();
                        LicenseAndOwner = gson.fromJson(response.toString(), listantyyppi);
                        ImageAndTextAdapter = new ImageAndTextAdapter(getApplicationContext(), R.layout.imageview,LicenseAndOwner ,response1);
                        listView.setAdapter(ImageAndTextAdapter);
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
