package com.example.er_ja.jave_pr10_fct.ui.visita;

import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class VisitaFragmentViewModel extends ViewModel {
    private Visita visita = null;
    private LiveData<List<String>> nombreAlumnos = null;
    private final Repository repository;

    public VisitaFragmentViewModel(Repository repository) {
        this.repository = repository;
        this.nombreAlumnos = repository.getNombreAlumnos();
    }

    public void insert(Visita visita){
        repository.insertVisita(visita);
    }

    public void update(Visita visita){
        repository.updateVisita(visita);
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public LiveData<List<String>> getNombreAlumnos(){
        return nombreAlumnos;
    }

    public Alumno getAlumno(String nombre){
        return repository.getAlumno(nombre);
    }
    public String getNombreAlumno(Long id){ return repository.getNombreAlumno(id);}
}
