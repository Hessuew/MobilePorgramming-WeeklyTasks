package com.example.a4_1assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private boolean disabled = true;
    private String editTexttext = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            editTexttext = savedInstanceState.getString("editTexttext");
            disabled = savedInstanceState.getBoolean("disabled");
        }

        editText = findViewById(R.id.editText);
        editText.setText(editTexttext);
        editText.setEnabled(disabled);
    }

    public void ButtonOnClick(View view){
        if (disabled == true ) {
            editText.setEnabled(false);
            disabled = false;
        } else{
            editText.setEnabled(true);
            disabled = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("editTexttext", editTexttext);
        savedInstanceState.putBoolean("disabled", disabled);
        super.onSaveInstanceState(savedInstanceState);
    }

}
