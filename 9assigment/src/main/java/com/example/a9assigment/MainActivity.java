package com.example.a9assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button gotoregisterButton;
    private EditText loginUsername;
    private String Username;
    private EditText loginPassword;
    private Context context;
    private List<MinunTaulu> list = new ArrayList<MinunTaulu>();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginButton);
        gotoregisterButton = findViewById(R.id.gotoregisterButton);
        loginUsername = findViewById(R.id.loginUsername);
        Username = loginUsername.getText().toString();
        loginPassword = findViewById(R.id.loginPassword);

        RoomDatabase.Builder<Tietokanta> rakentaja = Room.databaseBuilder(getApplicationContext(), Tietokanta.class, (String) Tietokanta.NIMI);

        final Tietokanta kanta = rakentaja.build();

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Username == "") {
                    Toast.makeText(context, "Username field is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    MinunTauluDao dataaccessobject = kanta.MinunTauluDao();
                    try{


                    list = dataaccessobject.getUsernameAndPass(loginUsername.getText().toString(), loginPassword.getText().toString());
                    if (list.isEmpty()) {
                        Toast.makeText(context, "Login failed: Username or password is incorrect", Toast.LENGTH_SHORT).show();
                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        MinunTaulu2 x = new MinunTaulu2();
                        x.Username = (loginUsername.getText().toString());
                        x.successfull = false;
                        x.timestamp = ts;
                        dataaccessobject.InsertLoginData(x);
                    } else {
                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        MinunTaulu2 x = new MinunTaulu2();
                        x.Username = (loginUsername.getText().toString());
                        x.successfull = true;
                        x.timestamp = ts;
                        dataaccessobject.InsertLoginData(x);

                        Intent i = new Intent(MainActivity.this, ContentActivity.class);
                        i.putExtra("Username", Username);

                        Toast.makeText(context, "Login successfull", Toast.LENGTH_SHORT).show();
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }
                    }catch (IllegalStateException e){
                        Toast.makeText(context, "Error accessing database. Our mice are on the work fixing the issue", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        gotoregisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },100);
            }
        });
    }
}
