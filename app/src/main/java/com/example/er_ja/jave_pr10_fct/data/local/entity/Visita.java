package com.example.er_ja.jave_pr10_fct.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "visita",
        foreignKeys = @ForeignKey(entity = Alumno.class,
                                parentColumns = "id",
                                childColumns = "id_alumno",
                                onUpdate = CASCADE,
                                onDelete = CASCADE))
public class Visita {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "dia")
    private String dia;
    @ColumnInfo(name = "hora_inicio")
    private String horaInicio;
    @ColumnInfo(name = "hora_fin")
    private String horaFin;
    @ColumnInfo(name = "comentario")
    private String comentario;
    @ColumnInfo(name = "id_alumno")
    private Long idAlumno;
    @Ignore
    private String nombreAlumno;

    public Visita(String dia, String horaInicio, String horaFin, String comentario, Long idAlumno) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.comentario = comentario;
        this.idAlumno = idAlumno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
}
