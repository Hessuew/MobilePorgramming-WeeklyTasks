package com.example.a9assigment.room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class RepositoryUser {
    private DaoUser daoUser;

    public RepositoryUser(Application app) {
        Database db = Database.getDatabase(app);
        daoUser = db.daoUser();
    }

    List<EntityUser> getUser(String username, String password) {
        return daoUser.getUser(username, password);
    }

    List<EntityUser> usernameTaken(String username) {
        return daoUser.usernameTaken(username);
    }

    List<EntityUser> getAllUsers() {
        return daoUser.getAllUsers();
    }

    public void insert(EntityUser entityUser) {
        new insertAsyncTask(daoUser).execute(entityUser);
    }

    private static class insertAsyncTask extends AsyncTask<EntityUser, Void, Void> {

        private DaoUser mAsyncTaskDao;

        insertAsyncTask(DaoUser dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EntityUser... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
