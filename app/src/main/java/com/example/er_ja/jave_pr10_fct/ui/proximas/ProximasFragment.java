package com.example.er_ja.jave_pr10_fct.ui.proximas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentProximasBinding;
import com.example.er_ja.jave_pr10_fct.ui.alumnos.AlumnosFragmentViewModel;
import com.example.er_ja.jave_pr10_fct.ui.alumnos.AlumnosFragmentViewModelFactory;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;
import com.example.er_ja.jave_pr10_fct.ui.visitas.VisitasFragmentViewModel;
import com.example.er_ja.jave_pr10_fct.ui.visitas.VisitasFragmentViewModelFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ProximasFragment extends Fragment {
    private ProximasFragmentViewModel vm;
    private VisitasFragmentViewModel visitasFragmentViewModel;
    private AlumnosFragmentViewModel alumnosFragmentViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private FragmentProximasBinding b;

    private ProximasFragmentAdapter listAdapter;

    public ProximasFragment() {
    }

    public static ProximasFragment newInstance(){
        return new ProximasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentProximasBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(ProximasFragment.this, new ProximasFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(ProximasFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        alumnosFragmentViewModel = ViewModelProviders.of(getActivity(), new AlumnosFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(AlumnosFragmentViewModel.class);
        visitasFragmentViewModel = ViewModelProviders.of(getActivity(), new VisitasFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(VisitasFragmentViewModel.class);
        //TODO CUIDAO CON EL S NULL
        mainActivityViewModel.setVisitaInsert(null);
        observeAlumnos();
        observeVisitas();
        setupViews();
    }

    private void observeAlumnos() {
        alumnosFragmentViewModel.getAlumnos().observe(getViewLifecycleOwner(), alumnos -> {

        });
    }

    private void observeVisitas() {
        vm.getVisitas().observe(this, visitas -> {
            b.lblEmptyView.setVisibility(visitas.isEmpty() ? View.VISIBLE : View.INVISIBLE);
            for (Visita visita:visitas) {
                visita.setNombreAlumno(vm.getNombreAlumno(visita.getIdAlumno()));
            }
            Collections.sort(visitas, new Comparator<Visita>() {
                @Override
                public int compare(Visita o1, Visita o2) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyHH:mm");
                    Date fecha1, fecha2;
                    try {
                        fecha1 = format.parse(o1.getDia()+o1.getHoraInicio());
                        fecha2 = format.parse(o2.getDia()+o2.getHoraInicio());
                        if(o1.getIdAlumno().equals(o2.getIdAlumno())){
                            return fecha2.compareTo(fecha1);
                        }else{
                            return o1.getIdAlumno().compareTo(o2.getIdAlumno());
                        }
                    } catch (ParseException e) {
                        System.out.println("Error al convertir las fechas");
                        return o2.getDia().compareTo(o1.getDia());
                    }
                }
            });

            List<Visita> listAux = new ArrayList<>();
            Long studentIdAux = -1L;
            for(Visita visita: visitas){
                if(!visita.getIdAlumno().equals(studentIdAux)){
                    Calendar calendar = Calendar.getInstance();
                    String dateNextVisit;
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha;
                    try {
                        fecha = df.parse(visita.getDia());
                        calendar.setTime(fecha);
                        calendar.add(Calendar.DAY_OF_YEAR, 15);
                        dateNextVisit = df.format(calendar.getTime());
                        Visita proxima = new Visita(dateNextVisit,null,null,null, visita.getIdAlumno());
                        proxima.setNombreAlumno(vm.getNombreAlumno(visita.getIdAlumno()));
                        listAux.add(proxima);
                    } catch (ParseException e) {
                        System.out.println("Error parse Date");
                    }
                }
                studentIdAux = visita.getIdAlumno();
            }

            alumnosFragmentViewModel.getAlumnos().observe(this, alumnos -> {
                boolean visitado;
                for(Alumno alumno:alumnos){
                    visitado=false;
                    for(Visita visita:listAux){
                        if(visita.getIdAlumno()==alumno.getId()){
                            visitado=true;
                        }
                    }
                    if(!visitado){
                        Visita sinVisitar = new Visita(null,null,null,null, alumno.getId());
                        sinVisitar.setNombreAlumno(alumno.getNombre());
                        listAux.add(sinVisitar);
                    }
                }
            });

            listAdapter.submitList(listAux);
        });
    }

    private void setupViews() {
        listAdapter = new ProximasFragmentAdapter((v, position) -> {
            editUser(listAdapter.getItem(position));
            Navigation.findNavController(v).navigate(R.id.visitaFragment);
        });
        b.lstProximas.setHasFixedSize(true);
        b.lstProximas.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.visitas_lstVisitas_columns)));
        b.lstProximas.setItemAnimator(new DefaultItemAnimator());
        b.lstProximas.setAdapter(listAdapter);
    }

    private void editUser(Visita visita) {
        mainActivityViewModel.setVisitaInsert(visita);
    }
}
