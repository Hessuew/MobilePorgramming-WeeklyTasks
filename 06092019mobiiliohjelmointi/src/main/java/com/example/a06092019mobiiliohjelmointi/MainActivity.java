package com.example.a06092019mobiiliohjelmointi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener{
    private Button mButton;
    private Button mButton2;
    private EditText editText;
    ArrayList<String> textlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById((R.id.button));
        mButton.setOnClickListener(this);
        editText = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View view){
        String message= "";
         if (editText.length()<3 || editText.length() > 15) {
             message = "Tekstin pituus pitää olla 3-15 merkkiä";
             Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
         } else{
             textlist.add(editText.getText().toString());
             message = "Sana lisättiin listaan";
             Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
         }
    }
    public void gotoActivity2(View view){
        Intent intent = new Intent(MainActivity.this, ShowList.class);
        intent.putStringArrayListExtra("textlist", textlist);
        startActivity(intent);
    }
}
