package com.example.a9assigment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a9assigment.room.ModelLoginLog;
import com.example.a9assigment.room.ModelUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
* Make an application which has three screens:

First screen displayed when you open the application is the log in-screen. In the screen you can do two things

1) Enter username and password to advance to the content screen.
2) Move to a screen where you can register a new username and a password.

In the password registeration screen you can either return back to the login page or enter a new username and password.
* Upon entering a new username successfully (the username was not in use before) you are returned to login screen.

On the login page if user enters username and password correctly the content page opens.
* Content page has only a button and a list of login attempts (failed and successful, with timestamps) of the current logged in user.
* The button logs the user out and returns to login page.

Usernames, passwords and login information is stored in SQLite database and it's accessed through Room-objects.
* */

public class MainActivity extends AppCompatActivity {
    private Toast loginFailedToast;
    private Button login;
    private FloatingActionButton register;
    private ModelUser modelUser;
    private ModelLoginLog modelLoginLog;
    private Intent openContent, openRegistering;
    private EditText username, password;
    private Activity ctx;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFailedToast = Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_LONG);
        openContent = new Intent(this, Content.class);
        openRegistering = new Intent(MainActivity.this, Register.class);
        modelUser = new ViewModelProvider(this).get(ModelUser.class);
        modelLoginLog = new ViewModelProvider(this).get(ModelLoginLog.class);

        // Views
        login = findViewById(R.id.login_login);
        register = findViewById(R.id.login_register);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        ctx = this;


        // Buttons functionality
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                modelUser.confirmLogin(usernameText, passwordText, openContent, ctx, loginFailedToast, modelLoginLog);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(openRegistering);
            }
        });
    }


}
