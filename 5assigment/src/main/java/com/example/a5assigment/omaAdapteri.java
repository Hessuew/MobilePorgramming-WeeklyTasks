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
import java.util.Date;

public class omaAdapteri extends ArrayAdapter<Date> {

    private int line_layout;
    private int textviewid;
    private ArrayList<Date> lista;
    private LayoutInflater inflater;
    private Context context;

    public omaAdapteri(@NonNull Context context,
                       int resource,
                       int textViewResourceId,
                       @NonNull ArrayList<Date> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        line_layout = resource;
        textviewid = textViewResourceId;
        lista = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v;
        if (convertView == null) {
            v = LayoutInflater.from(context).inflate(R.layout.listaitem, parent, false);
            ((TextView) v.findViewById(R.id.nimi)).setText(lista.get(position).toString());
        } else {
            v = convertView;
        }

        return v;
    }
}
