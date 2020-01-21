package com.example.a9assigment.room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.Date;
import java.util.List;

public class ModelUser extends AndroidViewModel {
    private RepositoryUser mRepository;

    public ModelUser(@NonNull Application application) {
        super(application);
        this.mRepository = new RepositoryUser(application);
    }

    public void insert(EntityUser user) {
        mRepository.insert(user);
    }

    @SuppressLint("StaticFieldLeak")
    public void registerUser(final String username, final Intent intent, final Activity activity, final Toast fail, final EntityUser user) {
        new AsyncTask<Void, Void, List<EntityUser>>() {
            @Override
            protected List<EntityUser> doInBackground(Void... params) {
                //runs on background thread
                return mRepository.usernameTaken(username);
            }

            @Override
            protected void onPostExecute(List<EntityUser> items) {
                //runs on main thread
                if (items.isEmpty()) {
                    mRepository.insert(user);
                    activity.startActivity(intent);
                } else {
                    fail.show();
                }
            }
        }.execute();
    }


    @SuppressLint("StaticFieldLeak")
    public void confirmLogin(final String username, final String password, final Intent intent, final Activity activity, final Toast fail, final ModelLoginLog modelLoginLog) {
        new AsyncTask<Void, Void, List<EntityUser>>() {
            @Override
            protected List<EntityUser> doInBackground(Void... params) {
                //runs on background thread
                return mRepository.getUser(username, password);
            }

            @Override
            protected void onPostExecute(List<EntityUser> items) {
                //runs on main thread
                if (items.isEmpty()) {
                    EntityLoginLog log = new EntityLoginLog();
                    log.username = username;
                    log.timestamp = new Date();
                    log.success = false;
                    modelLoginLog.insert(log);
                    fail.show();
                } else {
                    EntityLoginLog log = new EntityLoginLog();
                    log.username = username;
                    log.timestamp = new Date();
                    log.success = true;
                    modelLoginLog.insert(log);
                    intent.putExtra("username", username);
                    activity.startActivity(intent);
                }
            }
        }.execute();
    }
}

