package com.example.a9assigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {
    private Button contentbackButton;
    private Context context;
    private Handler handler;
    private ownAdapter adapter;
    private ListView listView;
    private MinunTaulu2 minunTaulu2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        contentbackButton = findViewById(R.id.contentbackButton);
        listView = findViewById(R.id.listView);

        String loginUsername = getIntent().getExtras().getString("Username","defaultKey");

        adapter = new ownAdapter(this, R.layout.content, minunTaulu2, loginUsername);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        contentbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(ContentActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },100);
            }
        });

    }



}
