package com.example.a9assigment.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoUser {
    // IF user tries to insert duplicate. It is just IGNORED!
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EntityUser user);

    @Query("SELECT * FROM EntityUser WHERE username = :username AND password = :password LIMIT 1; ")
    List<EntityUser> getUser(String username, String password);

    @Query("SELECT * FROM EntityUser WHERE username = :username LIMIT 1; ")
    List<EntityUser> usernameTaken(String username);

    @Query("SELECT * FROM EntityUser")
    List<EntityUser> getAllUsers();
}
