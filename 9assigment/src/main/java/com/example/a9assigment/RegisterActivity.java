package com.example.a9assigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private Button  registerButton;
    private Button backButton;
    private EditText registerUsername;
    private EditText registerPassword;
    private Context context;
    private List<MinunTaulu> list = new ArrayList<MinunTaulu>();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        registerUsername = findViewById(R.id.registerUsername);

        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);
        registerPassword = findViewById(R.id.registerPassword);
        RoomDatabase.Builder<Tietokanta> rakentaja = Room.databaseBuilder(getApplicationContext(), Tietokanta.class, (String) Tietokanta.NIMI);
        registerUsername.getText().toString().matches("");
        final Tietokanta kanta = rakentaja.build();

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (registerUsername.getText().toString().matches("") || registerPassword.getText().toString().matches("")) {
                    Toast.makeText(context, "Username or password fields cant be empty", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                    MinunTauluDao dataaccessobject = kanta.MinunTauluDao();
                    list = dataaccessobject.getUsernameAndPass(registerUsername.getText().toString(), registerPassword.getText().toString());
                    if (list.isEmpty()) {
                        Toast.makeText(context, "New user made successfully", Toast.LENGTH_SHORT).show();
                        MinunTaulu x = new MinunTaulu();
                        x.username = (registerUsername.getText().toString());
                        x.password = (registerPassword.getText().toString());
                        dataaccessobject.InsertMyEntity(x);

                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(context, "Username already in user", Toast.LENGTH_SHORT).show();
                    }
                }catch (IllegalStateException e){
                        Toast.makeText(context, "Error accessing database. Our mice are on the work fixing the issue", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },100);
            }
        });
    }
}
