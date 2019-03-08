package com.example.er_ja.jave_pr10_fct.ui.visitas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentVisitasBinding;
import com.example.er_ja.jave_pr10_fct.ui.alumnos.AlumnosFragmentViewModel;
import com.example.er_ja.jave_pr10_fct.ui.alumnos.AlumnosFragmentViewModelFactory;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class VisitasFragment extends Fragment {
    private VisitasFragmentViewModel vm;
    private AlumnosFragmentViewModel alumnosFragmentViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private FragmentVisitasBinding b;

    private VisitasFragmentAdapter listAdapter;

    public VisitasFragment() {
    }

    public static VisitasFragment newInstance() {
        return new VisitasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentVisitasBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(VisitasFragment.this, new VisitasFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(VisitasFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        alumnosFragmentViewModel = ViewModelProviders.of(getActivity(), new AlumnosFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(AlumnosFragmentViewModel.class);
        //TODO CUIDAO CON EL SETVISITA NULL
        mainActivityViewModel.setVisitaUpdate(null);
        setupViews();
        observeVisitas();
//        observeAlumnos();
    }

    private void observeVisitas() {
        vm.getVisitas().observe(this, visitas -> {
            b.lblEmptyView.setVisibility(visitas.isEmpty() ? View.VISIBLE : View.INVISIBLE);
            Collections.sort(visitas, new Comparator<Visita>() {
                @Override
                public int compare(Visita o1, Visita o2) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyHH:mm");
                    Date fecha1, fecha2;

                    try {
                        fecha1 = format.parse(o1.getDia() + o1.getHoraInicio());
                        fecha2 = format.parse(o2.getDia() + o2.getHoraInicio());
                        return fecha2.compareTo(fecha1);
                    } catch (ParseException e) {
                        System.out.println("Error al convertir las fechas");
                        return o2.getDia().compareTo(o1.getDia());
                    }
                }
            });

            for (Visita visita : visitas) {
                visita.setNombreAlumno(vm.getNombreAlumno(visita.getIdAlumno()));
            }
            listAdapter.submitList(visitas);
        });
    }
//    private void observeAlumnos() {
//        alumnosFragmentViewModel.getAlumnos().observe(this, alumnos -> {
//            if(alumnos.size()==0){
//                b.visitasFab.setOnClickListener(v -> Toast.makeText(getContext(),"Debe crear primero algún alumno", Toast.LENGTH_LONG).show());
//            }else{
//                b.visitasFab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_visitasFragment_to_visitaFragment));
//            }
//        });
//    }

    private void setupViews() {
        listAdapter = new VisitasFragmentAdapter((v, position) -> {
            editUser(listAdapter.getItem(position));
            Navigation.findNavController(v).navigate(R.id.visitaFragment);
        });
        b.lstVisitas.setHasFixedSize(true);
        b.lstVisitas.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.visitas_lstVisitas_columns)));
        b.lstVisitas.setItemAnimator(new DefaultItemAnimator());
        b.lstVisitas.setAdapter(listAdapter);

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
        itemTouchHelper.attachToRecyclerView(b.lstVisitas);
    }

    private void editUser(Visita visita) {
        mainActivityViewModel.setVisitaUpdate(visita);
    }
}
