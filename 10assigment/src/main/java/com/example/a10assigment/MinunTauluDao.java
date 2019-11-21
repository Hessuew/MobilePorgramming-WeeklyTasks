package com.example.a10assigment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MinunTauluDao {

    @Query("SELECT * FROM MinunTaulu ORDER BY id desc")
    List<MinunTaulu> getAllInDescendingOrder();

    @Insert
    void InsertMyEntity(MinunTaulu myentity);

    @Delete
    void DeleteMyEntity(MinunTaulu myentity);
}
