package com.example.er_ja.jave_pr10_fct.ui.visita;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.base.DatePickerDialogFragment;
import com.example.er_ja.jave_pr10_fct.base.TimePickerDialogFragment;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentVisitaBinding;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;


public class VisitaFragment extends Fragment {
    private FragmentVisitaBinding b;
    private VisitaFragmentViewModel vm;
    private MainActivityViewModel mainActivityViewModel;
    private ArrayAdapter<String> adaptadorAlumnos;

    public VisitaFragment() {
    }

    public static VisitaFragment newInstance() {
        VisitaFragment visitaFragment = new VisitaFragment();
        return visitaFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentVisitaBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(VisitaFragment.this, new VisitaFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(VisitaFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        observeAlumnos();
        setupViews();
    }

    private void observeAlumnos() {
        vm.getNombreAlumnos().observe(this, alumnos -> {
            adaptadorAlumnos = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, alumnos);
            // Se indica el layout que debe usarse para cada elemento de la lista
// cuando ésta se despliegue.
            adaptadorAlumnos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Se establece el adaptadorAlumnos para el spinner.
            b.visitaSpAlumno.setAdapter(adaptadorAlumnos);
            if(mainActivityViewModel.getVisita()!=null){
                b.visitaSpAlumno.setSelection(adaptadorAlumnos.getPosition(vm.getNombreAlumno(mainActivityViewModel.getVisita().getIdAlumno())));
            }
        });
    }

    private void setupViews() {

        b.visitaSpAlumno.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.visitaLblName.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.visitaLblName.setTypeface(Typeface.DEFAULT);
            }
        });

        b.visitaTxtComentarios.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.visitaLblComentarios.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.visitaLblComentarios.setTypeface(Typeface.DEFAULT);
            }
        });

        b.visitaTxtDia.setOnClickListener(v -> showDatePickerDialog());
        b.visitaTxtHoraInicio.setOnClickListener(v -> showTimePickerDialog(b.visitaTxtHoraInicio));
        b.visitaTxtHoraFin.setOnClickListener(v -> showTimePickerDialog(b.visitaTxtHoraFin));
        if (mainActivityViewModel.getVisita() == null) {
            b.visitaFab.setOnClickListener(v -> {
                try{
                    Alumno alu;
                    Long idAlu;
                    String nom;
                    if(b.visitaSpAlumno.getSelectedItem()!=null){
                        alu = vm.getAlumno(b.visitaSpAlumno.getSelectedItem().toString());
                        idAlu = alu.getId();
                        nom = alu.getNombre();
                    }else{
                        nom="";
                        idAlu=null;
                    }
                    Visita visita = new Visita(
                            b.visitaTxtDia.getText().toString(),
                            b.visitaTxtHoraInicio.getText().toString(),
                            b.visitaTxtHoraFin.getText().toString(),
                            b.visitaTxtComentarios.getText().toString(),
                            idAlu);
                    visita.setNombreAlumno(nom);
                    vm.insert(visita);
                    Toast.makeText(getContext(),"Visita guardada",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.visitasFragment);
                }catch (android.database.sqlite.SQLiteConstraintException e){
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(),"Error al guardar la visita",Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            rellenarCampos();
            b.visitaFab.setOnClickListener(v -> {
                try{
                    Alumno alu;
                    Long idAlu;
                    String nom;
                    if(b.visitaSpAlumno.getSelectedItem()!=null){
                        alu = vm.getAlumno(b.visitaSpAlumno.getSelectedItem().toString());
                        idAlu = alu.getId();
                        nom = alu.getNombre();
                    }else{
                        nom="";
                        idAlu=null;
                    }
                    Visita visita = mainActivityViewModel.getVisita();
                    visita.setDia(b.visitaTxtDia.getText().toString());
                    visita.setHoraInicio(b.visitaTxtHoraInicio.getText().toString());
                    visita.setHoraFin(b.visitaTxtHoraFin.getText().toString());
                    visita.setComentario(b.visitaTxtComentarios.getText().toString());
                    visita.setIdAlumno(idAlu);
                    visita.setNombreAlumno(nom);
                    vm.update(visita);
                    Toast.makeText(getContext(),"Visita actualizada",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.visitasFragment);
                }catch (android.database.sqlite.SQLiteConstraintException e){
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(),"Error al actualizar la visita",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void showTimePickerDialog(EditText editText) {
        TimePickerDialogFragment newFragment = TimePickerDialogFragment.newInstance((view, hourOfDay, minute) -> {
            final String selectHour = twoDigits(hourOfDay) + ":"+ twoDigits(minute);
            editText.setText(selectHour);
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    private void showDatePickerDialog() {
        DatePickerDialogFragment newFragment = DatePickerDialogFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = twoDigits(dayOfMonth) + "/" + twoDigits(month+1) + "/" + year;
            b.visitaTxtDia.setText(selectedDate);
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    private void rellenarCampos() {
        Visita visita = mainActivityViewModel.getVisita();
        b.visitaTxtDia.setText(visita.getDia());
        b.visitaTxtHoraInicio.setText(visita.getHoraInicio());
        b.visitaTxtHoraFin.setText(visita.getHoraFin());
        b.visitaTxtComentarios.setText(visita.getComentario());

    }

}
