package com.example.er_ja.jave_pr10_fct.ui.alumnos;

import com.example.er_ja.jave_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AlumnosFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public AlumnosFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AlumnosFragmentViewModel(repository);
    }
}
