package com.example.er_ja.jave_pr10_fct.data.local;

import android.content.Context;

import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Alumno.class, Empresa.class, Visita.class}, version = 18)
public abstract class Database extends RoomDatabase {

    private static final String DATABASE_NAME = "fct.db";
    private static volatile Database instance;
    public abstract AlumnoDao alumnoDao();
    public abstract EmpresaDao empresaDao();
    public abstract VisitaDao visitaDao();

    public static Database getInstance(Context context){
        if(instance==null){
            synchronized (Database.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),Database.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
