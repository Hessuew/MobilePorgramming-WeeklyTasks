package com.example.a10assigment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private JulistuksenKuuntelija julistuksenKuuntelija;
    private IntentFilter intentFilter;
    private IntentFilter intentFilter2;
    private Context context;
    private Intent intentti;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        julistuksenKuuntelija = new JulistuksenKuuntelija();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Intent.ACTION_SCREEN_OFF);
        context.registerReceiver(julistuksenKuuntelija, intentFilter);
        context.registerReceiver(julistuksenKuuntelija, intentFilter2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerReceiver(julistuksenKuuntelija, intentFilter2);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(julistuksenKuuntelija, intentFilter);
    }

    private class JulistuksenKuuntelija extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("ACTION_SCREEN_ON")) {
                String message = "auki";
                intentti.putExtra("message", message);
                intentti = new Intent(MainActivity.this, IntentPalvelu.class);
                startService(intentti);

            } else if(intent.getAction().equals("ACTION_SCREEN_OFF")){
                String message = "kiinni";
                intentti.putExtra("message", message);
                intentti = new Intent(MainActivity.this, IntentPalvelu.class);
                startService(intentti);
            }
        }
    }
}
