package com.example.er_ja.jave_pr10_fct.ui.alumnos;

import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AlumnosFragmentViewModel extends ViewModel {
    private final Repository repository;
    private LiveData<List<Alumno>> alumnos;

    public AlumnosFragmentViewModel(Repository repository) {
        this.repository = repository;
        alumnos = repository.getAlumnos();
    }

    public void delete(Alumno alumno){
        repository.deleteAlumno(alumno);
    }

    public LiveData<List<Alumno>> getAlumnos(){
        return alumnos;
    }
    public String getNombreEmpresa(Long id){ return repository.getNombreEmpresa(id);}

}
