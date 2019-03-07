package com.example.er_ja.jave_pr10_fct.ui.empresa;

import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;

import androidx.lifecycle.ViewModel;

public class EmpresaFragmentViewModel extends ViewModel {
    private final Repository repository;

    public EmpresaFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void insert(Empresa empresa){
        repository.insertEmpresa(empresa);
    }

    public void update(Empresa empresa){
        repository.updateEmpresa(empresa);
    }
}
