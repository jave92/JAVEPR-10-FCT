package com.example.er_ja.jave_pr10_fct.data.local;

import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AlumnoDao {
    @Insert
    void insert(Alumno alumno);
    @Update
    void update(Alumno alumno);
    @Delete
    void delete(Alumno alumno);
    @Query("SELECT * FROM alumno ORDER BY nombre")
    LiveData<List<Alumno>> getAlumnos();
    @Query("SELECT nombre FROM alumno ORDER BY nombre")
    LiveData<List<String>> getNombreAlumnos();
    @Query("SELECT * FROM alumno WHERE nombre=:nombre")
    Alumno getAlumno(String nombre);
    @Query("SELECT nombre FROM alumno WHERE id=:id")
    String getNombreAlumno(Long id);
}
