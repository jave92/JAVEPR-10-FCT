package com.example.er_ja.jave_pr10_fct.ui.alumno;

import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AlumnoFragmentViewModel extends ViewModel {
    private Alumno alumno = null;
    private LiveData<List<String>> nombreEmpresas = null;
    private final Repository repository;

    public AlumnoFragmentViewModel(Repository repository) {
        this.repository = repository;
        this.nombreEmpresas = repository.getNombreEmpresas();
    }

    public void insert(Alumno alumno){
        repository.insertAlumno(alumno);
    }

    public void update(Alumno alumno){
        repository.updateAlumno(alumno);
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LiveData<List<String>> getNombreEmpresas(){
        return nombreEmpresas;
    }

    public Empresa getEmpresa(String nombre){
        return repository.getEmpresa(nombre);
    }
    public String getNombreEmpresa(Long id){ return repository.getNombreEmpresa(id);}
}
