package com.example.a11assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MainActivity extends ListActivity {
    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 6226;
    final int PICK_CONTACT = 112;
    private String name;
    private Intent intent;
    private ArrayList list = new ArrayList();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);
        Button btnDel = (Button) findViewById(R.id.btnDel);


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int check = checkSelfPermission( Manifest.permission.READ_CONTACTS);
                if (check == PackageManager.PERMISSION_GRANTED) {
                    intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);                }

            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        });
        setListAdapter(adapter);
}

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use
                        if(list.contains(name)){
                            Toast.makeText(getApplicationContext(), "Nimi on jo listassa!", Toast.LENGTH_SHORT).show();
                        }else{
                            list.add(name);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                } else {
                    Toast.makeText(getApplicationContext(), "No can do!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
