package com.example.a9assigment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a9assigment.room.ModelLoginLog;
import com.example.a9assigment.room.RwAdapterLoginLog;

public class Content extends AppCompatActivity {
    private Button logout;
    private ModelLoginLog modelLoginLog;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        username = getIntent().getStringExtra("username");


        // Views
        logout = findViewById(R.id.content_logout);

        // RecyclerView
        // NameDate RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        final RwAdapterLoginLog adapter = new RwAdapterLoginLog(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Database
        modelLoginLog = new ViewModelProvider(this).get(ModelLoginLog.class);
        modelLoginLog.showData(adapter, username);

        // Button functionality
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Content.this, MainActivity.class);
                Content.this.startActivity(intent);
            }
        });
    }
}
