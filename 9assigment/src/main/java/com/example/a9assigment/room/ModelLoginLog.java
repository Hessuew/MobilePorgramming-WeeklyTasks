package com.example.a9assigment.room;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ModelLoginLog extends AndroidViewModel {
    private RepositoryLoginLog mRepository;

    public ModelLoginLog(@NonNull Application application) {
        super(application);
        this.mRepository = new RepositoryLoginLog(application);
    }


    void insert(EntityLoginLog login) {
        mRepository.insert(login);
    }


    @SuppressLint("StaticFieldLeak")
    public void showData(final RwAdapterLoginLog adapter, final String username) {
        new AsyncTask<Void, Void, List<EntityLoginLog>>() {
            @Override
            protected List<EntityLoginLog> doInBackground(Void... params) {
                //runs on background thread
                return mRepository.getUserLoginAttempts(username);
            }

            @Override
            protected void onPostExecute(List<EntityLoginLog> items) {
                //runs on main thread
                adapter.setNameDates(items);
            }
        }.execute();
    }
}
