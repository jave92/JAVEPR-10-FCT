package com.example.er_ja.jave_pr10_fct.ui.proximas;

import com.example.er_ja.jave_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProximasFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public ProximasFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProximasFragmentViewModel(repository);
    }
}
