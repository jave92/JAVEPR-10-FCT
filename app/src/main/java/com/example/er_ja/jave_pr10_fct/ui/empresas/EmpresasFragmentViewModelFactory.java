package com.example.er_ja.jave_pr10_fct.ui.empresas;

import com.example.er_ja.jave_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EmpresasFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public EmpresasFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EmpresasFragmentViewModel(repository);
    }
}
