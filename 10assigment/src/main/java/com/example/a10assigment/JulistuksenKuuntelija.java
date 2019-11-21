package com.example.a10assigment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

public class JulistuksenKuuntelija extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("ACTION_SCREEN_ON")) {

        } else{

        }
    }
}
