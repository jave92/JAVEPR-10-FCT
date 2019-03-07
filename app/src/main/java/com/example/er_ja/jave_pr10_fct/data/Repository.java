package com.example.er_ja.jave_pr10_fct.data;

import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {
    //Alumnos
    LiveData<List<Alumno>> getAlumnos();
    void insertAlumno(Alumno alumno);
    void updateAlumno(Alumno alumno);
    void deleteAlumno(Alumno alumno);
    Alumno getAlumno(String nombre);
    String getNombreAlumno(Long id);

    //Empresas
    LiveData<List<Empresa>> getEmpresas();
    LiveData<List<String>> getNombreEmpresas();
    void insertEmpresa(Empresa empresa);
    void updateEmpresa(Empresa empresa);
    void deleteEmpresa(Empresa empresa);
    Empresa getEmpresa(String nombre);
    String getNombreEmpresa(Long id);

    //Visitas
    LiveData<List<Visita>> getVisitas();
    LiveData<List<String>> getNombreAlumnos();
    void insertVisita(Visita visita);
    void updateVisita(Visita visita);
    void deleteVisita(Visita visita);
}
