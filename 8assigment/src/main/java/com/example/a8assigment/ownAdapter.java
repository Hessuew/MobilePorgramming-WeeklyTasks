package com.example.a8assigment;

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

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class ownAdapter extends ArrayAdapter<MinunTaulu> {

    private Context context;
    private int layoutResourceId;
    private MinunTaulu minunTaulu;

    public ownAdapter (Context context, int layoutResourceId, MinunTaulu minunTaulu){
        super(context, layoutResourceId);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    private class ViewHolder{
        TextView text;
        TextView text2;
        ImageView image;
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

            convertView = inflater.inflate(R.layout.listtemplate, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.text =  convertView.findViewById(R.id.textViewUrl);
            viewHolder.text2 = convertView.findViewById(R.id.textViewName);
            viewHolder.image = convertView.findViewById(R.id.imgView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        System.out.println(position);
        viewHolder.text.setText("Owner: " + dataaccessobject.getOwner());
        viewHolder.text2.setText("License: " + dataaccessobject.getLicense());

        //Toteutus picassolla ja pois kommentoituna Asynctaskin avulla.

        Picasso.get().load((Uri) dataaccessobject.getUrl()).into(viewHolder.image);

        //new DownloadImageTask(viewHolder.image).execute(itemList.get(position).getUrl());

        return convertView;

    }

    // Vaihtoehtoinen tapa hakea kuva
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
