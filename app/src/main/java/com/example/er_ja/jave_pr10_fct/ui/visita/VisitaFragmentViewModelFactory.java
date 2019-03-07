package com.example.er_ja.jave_pr10_fct.ui.visita;

import com.example.er_ja.jave_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class VisitaFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public VisitaFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VisitaFragmentViewModel(repository);
    }
}
