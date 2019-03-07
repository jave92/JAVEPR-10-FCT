package com.example.er_ja.jave_pr10_fct.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.PrimaryKey;

@DatabaseView
public class Proximas {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @ColumnInfo(name = "alumno")
    public String alumno;
    @ColumnInfo(name = "fecha")
    public String fecha;

    public Proximas(Long id, String alumno, String fecha) {
        this.id = id;
        this.alumno = alumno;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

