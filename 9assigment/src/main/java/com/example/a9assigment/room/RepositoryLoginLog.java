package com.example.a9assigment.room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class RepositoryLoginLog {
    private DaoLoginLog daoLoginLog;

    public RepositoryLoginLog(Application app) {
        Database db = Database.getDatabase(app);
        daoLoginLog = db.daoLoginLog();
    }

    List<EntityLoginLog> getUserLoginAttempts(String username) {
        return daoLoginLog.getUserLoginAttempts(username);
    }

    public void insert(EntityLoginLog login) {
        new insertAsyncTask(daoLoginLog).execute(login);
    }

    private static class insertAsyncTask extends AsyncTask<EntityLoginLog, Void, Void> {
        private DaoLoginLog mAsyncTaskDao;

        insertAsyncTask(DaoLoginLog dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(EntityLoginLog... entityLoginLogs) {
            mAsyncTaskDao.insert(entityLoginLogs[0]);
            return null;
        }
    }
}