package com.example.er_ja.jave_pr10_fct.ui.empresas;

import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class EmpresasFragmentViewModel extends ViewModel {
    private final Repository repository;
    LiveData<List<Empresa>> empresas;

    public EmpresasFragmentViewModel(Repository repository) {
        this.repository = repository;
        empresas = repository.getEmpresas();
    }

    public void delete(Empresa empresa){
        repository.deleteEmpresa(empresa);
    }

    public LiveData<List<Empresa>> getEmpresas(){
        return empresas;
    }
}
