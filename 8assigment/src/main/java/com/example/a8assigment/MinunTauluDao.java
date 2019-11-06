package com.example.a8assigment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MinunTauluDao {

    @Query("SELECT * FROM MinunTaulu ORDER BY id desc")
    List<MinunTaulu> getAllInDescendingOrder();

    @Query("SELECT owner FROM MinunTaulu")
    List<MinunTaulu> getOwner();

    @Query("SELECT license FROM MinunTaulu")
    List<MinunTaulu> getLicense();

    @Query("SELECT url FROM MinunTaulu")
    List<MinunTaulu> getUrl();

    @Insert
    void InsertMyEntity(MinunTaulu myentity);

    @Delete
    void DeleteMyEntity(MinunTaulu myentity);
}
