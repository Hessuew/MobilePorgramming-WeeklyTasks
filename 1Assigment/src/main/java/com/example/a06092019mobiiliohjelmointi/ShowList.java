package com.example.a06092019mobiiliohjelmointi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowList extends AppCompatActivity
        implements View.OnClickListener{
    private Button mButton;
    private TextView textView;
    ArrayList<String> textlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        mButton = findViewById((R.id.button3));
        mButton.setOnClickListener(this);
        textView = findViewById(R.id.textView2);
        textlist = getIntent().getStringArrayListExtra("textlist");

        textView.setText("");
        for (int i = 0; i < textlist.size(); i++){
            textView.append(textlist.get(i) + "\n");
        }
    }

    @Override
    public void onClick(View view){

        switch(view.getId()){
            case R.id.button3:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowList.this);
                alertDialogBuilder.setTitle("Palaa aiempaan näkymään");
                alertDialogBuilder
                        .setMessage("Haluatko varmasti palata?")
                        .setCancelable(false)
                        .setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ShowList.this.finish();
                                gotoActivity();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
        }
    }
    private void gotoActivity(){
        Intent intent = new Intent(ShowList.this, MainActivity.class);
        startActivity(intent);
    }
}
