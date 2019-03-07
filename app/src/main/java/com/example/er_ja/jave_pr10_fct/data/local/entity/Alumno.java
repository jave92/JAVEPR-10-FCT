package com.example.er_ja.jave_pr10_fct.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.SET_NULL;

@Entity(tableName = "alumno",
        indices = {@Index(value = {"nombre"},
                            unique = true)}
                            ,
        foreignKeys = @ForeignKey(entity = Empresa.class,
                parentColumns = "id",
                childColumns = "id_empresa",
                onUpdate = SET_NULL,
                onDelete = SET_NULL)
)
public class Alumno {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "telefono")
    private String telefono;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "curso")
    private String curso;
    @ColumnInfo(name = "id_empresa")
    private Long idEmpresa;
    @Ignore
    private String nombreEmpresa;
    @Embedded
    private Tutor tutor;

    public Alumno(String nombre, String telefono, String email, String curso, Long idEmpresa, Tutor tutor) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.curso = curso;
        this.idEmpresa=idEmpresa;
        this.tutor = tutor;
    }

    public static class Tutor {
        public Tutor(String nombre, String telefono, String horario) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.horario = horario;
        }

        @ColumnInfo(name = "nombre_tutor")
        private String nombre;
        @ColumnInfo(name = "telefono_tutor")
        private String telefono;
        @ColumnInfo(name = "horario_tutor")
        private String horario;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getHorario() {
            return horario;
        }

        public void setHorario(String horario) {
            this.horario = horario;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}

