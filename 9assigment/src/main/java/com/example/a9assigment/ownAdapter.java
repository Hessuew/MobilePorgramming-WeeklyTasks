package com.example.a9assigment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ownAdapter extends ArrayAdapter<MinunTaulu2> {

    private Context context;
    private int layoutResourceId;
    private MinunTaulu2 minunTaulu2;
    private List<MinunTaulu2> list = new ArrayList<MinunTaulu2>();
    private String loginUsername;
    ArrayList<MinunTaulu2> al = new ArrayList<MinunTaulu2>();

    public ownAdapter (Context context, int layoutResourceId, MinunTaulu2 minunTaulu2, String loginUsername){
        super(context, layoutResourceId);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.loginUsername = loginUsername;
    }

    private class ViewHolder{
        TextView text;
        TextView text2;
        TextView text3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        RoomDatabase.Builder<Tietokanta> rakentaja = Room.databaseBuilder(context.getApplicationContext(), Tietokanta.class, (String) Tietokanta.NIMI);
        File file = new File("path");

        Tietokanta kanta = rakentaja.build();

        MinunTauluDao dataaccessobject = kanta.MinunTauluDao();

        final ViewHolder viewHolder;

        if(convertView==null){

            convertView = inflater.inflate(R.layout.content, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.text =  convertView.findViewById(R.id.textViewUser);
            viewHolder.text2 = convertView.findViewById(R.id.textViewSuccessfull);
            viewHolder.text3 = convertView.findViewById(R.id.textViewTimestamp);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        list = dataaccessobject.getUserLogins(loginUsername);
            System.out.println(position);
            viewHolder.text.setText("User: " + list.get(position).Username);
            viewHolder.text2.setText("Successfull: " + list.get(position).successfull);
            viewHolder.text3.setText("Timestamp: " + list.get(position).timestamp);

        return convertView;

    }
}
