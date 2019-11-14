package com.example.a5assigment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class OmaAdapteri extends ArrayAdapter {
    private ArrayList<dada> lista;
    private Context context;

    public OmaAdapteri(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        lista = (ArrayList<dada>) objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            View v = LayoutInflater.from(context).inflate(R.layout.listaitem, parent, false);
            TextView t1 = v.findViewById(R.id.datetxt);
            TextView t2 = v.findViewById(R.id.nimitxt);
            t1.setText(lista.get(position).nimi);
            t2.setText(lista.get(position).pvm);
            convertView = v;
        }
        return convertView;
    }
}
