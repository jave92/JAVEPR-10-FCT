package com.example.er_ja.jave_pr10_fct.ui.alumno;

import com.example.er_ja.jave_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AlumnoFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public AlumnoFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AlumnoFragmentViewModel(repository);
    }
}
