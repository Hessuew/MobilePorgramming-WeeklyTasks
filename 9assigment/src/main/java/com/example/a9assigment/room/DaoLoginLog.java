package com.example.a9assigment.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoLoginLog {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EntityLoginLog listItem);

    @Query("SELECT * FROM login_log WHERE username = :username;")
    List<EntityLoginLog> getUserLoginAttempts(String username);
}
