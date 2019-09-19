package com.example.a2assigment3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean button2enabled = true;
    private int image1hidden = 0;
    private Button button1 = findViewById((R.id.button));
    private  Button button2 = findViewById(R.id.button2);
    private ImageView image = findViewById(R.id.image1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button3OnClick(v);
                return true;
            }
        });
    }
    public void Button1OnClick(View view){
        button1.setEnabled(button2enabled);

        if (button2enabled = true) {
            button2enabled= false;
        } else{
            button2enabled= true;
        }
    }
    public void Button2OnClick(View view){
      image.setVisibility(View.INVISIBLE);
      image1hidden = 4;
    }
    public void Button3OnClick(View view){
        if(image1hidden == 4)
            image.setVisibility(View.VISIBLE);
        image1hidden = 0;
    }
}
