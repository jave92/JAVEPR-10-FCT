package com.example.er_ja.jave_pr10_fct.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "empresa",
        indices = {@Index(value = {"nombre"},
                    unique = true)})
public class Empresa {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "cif")
    private String CIF;
    @ColumnInfo(name = "direccion")
    private String direccion;
    @ColumnInfo(name = "telefono")
    private String telefono;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "logo")
    private String urlLogo;
    @ColumnInfo(name = "contacto")
    private String nombreContacto;

    public Empresa(String nombre, String CIF, String direccion, String telefono, String email, String urlLogo, String nombreContacto) {
        this.nombre = nombre;
        this.CIF = CIF;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.urlLogo = urlLogo;
        this.nombreContacto = nombreContacto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
}
