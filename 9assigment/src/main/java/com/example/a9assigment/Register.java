package com.example.a9assigment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a9assigment.room.EntityUser;
import com.example.a9assigment.room.ModelUser;

public class Register extends AppCompatActivity {
    private Button register, cancel;
    private ModelUser modelUser;
    private EditText username, password, password2;
    private Intent loginIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Database
        modelUser = new ViewModelProvider(this).get(ModelUser.class);

        // Views
        register = findViewById(R.id.register_register);
        cancel = findViewById(R.id.register_cancel);
        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        password2 = findViewById(R.id.register_password2);

        loginIntent = new Intent(Register.this, MainActivity.class);


        // Button functionality
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() < 1) {
                    getToast("Empty Username!(min. length 1)").show();
                    return;
                }
                if (password.getText().toString().length() < 1) {
                    getToast("Empty Password!(min. length 1)").show();
                    return;
                }
                if (!password.getText().toString().equals(password2.getText().toString())) {
                    getToast("Passwords don't match!").show();
                    return;
                }

                EntityUser user = new EntityUser();
                user.username = username.getText().toString();
                user.password = password.getText().toString();

                modelUser.registerUser(username.getText().toString(), loginIntent, Register.this, getToast("Username already exists!"), user);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.this.startActivity(loginIntent);
            }
        });

    }

    Toast getToast(String text) {
        return Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
    }

}
