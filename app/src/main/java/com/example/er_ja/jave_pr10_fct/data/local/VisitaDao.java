package com.example.er_ja.jave_pr10_fct.data.local;

import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface VisitaDao {
    @Insert
    void insert(Visita visita);
    @Update
    void update(Visita visita);
    @Delete
    void delete(Visita visita);
    @Query("SELECT * FROM visita ORDER BY id_alumno")
    LiveData<List<Visita>> getVisitas();
}
