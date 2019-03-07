package com.example.er_ja.jave_pr10_fct.ui.visitas;

import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class VisitasFragmentViewModel extends ViewModel {
    private final Repository repository;
    LiveData<List<Visita>> visitas;

    public VisitasFragmentViewModel(Repository repository) {
        this.repository = repository;
        visitas = repository.getVisitas();
    }

    public void delete(Visita visita){
        repository.deleteVisita(visita);
    }

    public LiveData<List<Visita>> getVisitas(){
        return visitas;
    }
    public String getNombreAlumno(Long id){ return repository.getNombreAlumno(id);}

}
