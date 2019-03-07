package com.example.er_ja.jave_pr10_fct.ui.alumnos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.Repository;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.Database;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentAlumnosBinding;
import com.example.er_ja.jave_pr10_fct.ui.EditInterface;
import com.example.er_ja.jave_pr10_fct.ui.alumno.AlumnoFragment;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class AlumnosFragment extends Fragment {
    AlumnosFragmentViewModel vm;
    MainActivityViewModel mainActivityViewModel;
    FragmentAlumnosBinding b;

    private AlumnosFragmentAdapter listAdapter;

    public AlumnosFragment() {
    }

    public static AlumnosFragment newInstance(){
        return new AlumnosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentAlumnosBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(AlumnosFragment.this, new AlumnosFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(AlumnosFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        //TODO CUIDAO CON EL SETALUMNO NULL
        mainActivityViewModel.setAlumno(null);
        setupViews();
        observeAlumnos();
    }

    private void observeAlumnos() {
        vm.getAlumnos().observe(this, alumnos -> {
            for (Alumno alumno:alumnos) {
                alumno.setNombreEmpresa(vm.getNombreEmpresa(alumno.getIdEmpresa()));
            }
            listAdapter.submitList(alumnos);
        });
    }

    private void setupViews() {
        listAdapter = new AlumnosFragmentAdapter((v, position) -> {
            editUser(listAdapter.getItem(position));
            Navigation.findNavController(v).navigate(R.id.alumnoFragment);
        });
        b.lstAlumnos.setHasFixedSize(true);
        b.lstAlumnos.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.alumnos_lstAlumnos_columns)));
        b.lstAlumnos.setItemAnimator(new DefaultItemAnimator());
        b.lstAlumnos.setAdapter(listAdapter);
        b.alumnosFab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_alumnosFragment_to_alumnoFragment));

        // Se crea el helper.
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    // Cuando se detecta un gesto swipe to dismiss.
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        vm.delete(listAdapter.getItem(viewHolder.getAdapterPosition()));
                    }
                });
        // Se enlaza con el RecyclerView.
        itemTouchHelper.attachToRecyclerView(b.lstAlumnos);
    }

    private void editUser(Alumno alumno) {
        mainActivityViewModel.setAlumno(alumno);
    }
}
