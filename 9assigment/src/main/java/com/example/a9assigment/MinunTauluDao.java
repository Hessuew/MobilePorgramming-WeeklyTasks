package com.example.a9assigment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MinunTauluDao {

    @Query("SELECT * FROM MinunTaulu ORDER BY id desc")
    List<MinunTaulu> getAllInDescendingOrder();

    @Query("SELECT * FROM MinunTaulu WHERE username = :UN AND password = :PASS")
    List<MinunTaulu> getUsernameAndPass(String UN, String PASS);

    @Query("SELECT * FROM MinunTaulu2 WHERE username = :UN")
    List<MinunTaulu2> getUserLogins(String UN);

    @Insert
    void InsertMyEntity(MinunTaulu myentity);

    @Delete
    void DeleteMyEntity(MinunTaulu myentity);

    @Insert
    void InsertLoginData(MinunTaulu2 myentity);
}
