package com.example.er_ja.jave_pr10_fct.ui.alumno;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentAlumnoBinding;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import static com.example.er_ja.jave_pr10_fct.utils.ValidationUtils.isValidEmail;
import static com.example.er_ja.jave_pr10_fct.utils.ValidationUtils.isValidPhone;

public class AlumnoFragment extends Fragment {
    private FragmentAlumnoBinding b;
    private AlumnoFragmentViewModel vm;
    private MainActivityViewModel mainActivityViewModel;
    private ArrayAdapter<String> adaptadorEmpresas;

    public AlumnoFragment() {
    }

    public static AlumnoFragment newInstance() {
        AlumnoFragment alumnoFragment = new AlumnoFragment();
        return alumnoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentAlumnoBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(AlumnoFragment.this, new AlumnoFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(AlumnoFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        observeEmpresas();
        setupViews();
    }

    private void observeEmpresas() {
        vm.getNombreEmpresas().observe(this, empresas -> {
            adaptadorEmpresas = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, empresas);
            // Se indica el layout que debe usarse para cada elemento de la lista
// cuando ésta se despliegue.
            adaptadorEmpresas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Se establece el adaptadorEmpresas para el spinner.
            b.spEmpresa.setAdapter(adaptadorEmpresas);
            if(mainActivityViewModel.getAlumno()!=null){
                b.spEmpresa.setSelection(adaptadorEmpresas.getPosition(vm.getNombreEmpresa(mainActivityViewModel.getAlumno().getIdEmpresa())));
            }
        });
    }

    private void setupViews() {

        b.alumnoTxtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.alumnoLblName.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.alumnoLblName.setTypeface(Typeface.DEFAULT);
            }
        });

        b.alumnoTxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateName();
            }
        });

        b.alumnoTxtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.alumnoLblPhonenumber.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.alumnoLblPhonenumber.setTypeface(Typeface.DEFAULT);
            }
        });

        b.alumnoTxtPhonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePhonenumber();
            }
        });

        b.alumnoTxtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.alumnoLblEmail.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.alumnoLblEmail.setTypeface(Typeface.DEFAULT);
            }
        });
        b.alumnoTxtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });

        b.alumnoTxtNameTutor.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.alumnoLblNameTutor.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.alumnoLblNameTutor.setTypeface(Typeface.DEFAULT);
            }
        });

        b.alumnoTxtNameTutor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateTutorName();
            }
        });

        b.alumnoTxtPhonenumberTutor.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.alumnoLblPhoneTutor.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.alumnoLblPhoneTutor.setTypeface(Typeface.DEFAULT);
            }
        });

        b.alumnoTxtPhonenumberTutor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateTutorPhonenumber();
            }
        });
        mostrarTecladoVirtual(b.alumnoTxtName);
        if (mainActivityViewModel.getAlumno() == null) {
            b.alumnoFab.setOnClickListener(v -> {
                try{
                    Empresa emp;
                    Long idEmp;
                    String nom;
                    if(b.spEmpresa.getSelectedItem()!=null){
                        emp = vm.getEmpresa(b.spEmpresa.getSelectedItem().toString());
                        idEmp = emp.getId();
                        nom = emp.getNombre();
                    }else{
                        nom="";
                        idEmp=null;
                    }
                    Alumno alumno = new Alumno(
                            b.alumnoTxtName.getText().toString(),
                            b.alumnoTxtPhonenumber.getText().toString(),
                            b.alumnoTxtEmail.getText().toString(),
                            b.spCurso.getSelectedItem().toString(),
                            idEmp,
                            new Alumno.Tutor(
                                    b.alumnoTxtNameTutor.getText().toString(),
                                    b.alumnoTxtPhonenumberTutor.getText().toString(),
                                    b.alumnoSpHorarioTutor.getSelectedItem().toString()));
                    alumno.setNombreEmpresa(nom);
                    vm.insert(alumno);
                    Toast.makeText(getContext(),"Alumno insertado",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.alumnosFragment);
                }catch (android.database.sqlite.SQLiteConstraintException e){
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(),"Error al guardar el alumno",Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            rellenarCampos();
            b.alumnoFab.setOnClickListener(v -> {
                try{
                    Empresa emp;
                    Long idEmp;
                    String nom;
                    if(b.spEmpresa.getSelectedItem()!=null){
                        emp = vm.getEmpresa(b.spEmpresa.getSelectedItem().toString());
                        idEmp = emp.getId();
                        nom = emp.getNombre();
                    }else{
                        nom="";
                        idEmp=null;
                    }
                    Alumno alumno = mainActivityViewModel.getAlumno();
                    alumno.setNombre(b.alumnoTxtName.getText().toString());
                    alumno.setTelefono(b.alumnoTxtPhonenumber.getText().toString());
                    alumno.setEmail(b.alumnoTxtEmail.getText().toString());
                    alumno.setCurso(b.spCurso.getSelectedItem().toString());
                    alumno.setIdEmpresa(idEmp);
                    alumno.setNombreEmpresa(nom);
                    alumno.setTutor(new Alumno.Tutor(
                            b.alumnoTxtNameTutor.getText().toString(),
                            b.alumnoTxtPhonenumberTutor.getText().toString(),
                            b.alumnoSpHorarioTutor.getSelectedItem().toString()));
                    vm.update(alumno);
                    Toast.makeText(getContext(),"Alumno actualizado",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.alumnosFragment);
                }catch (android.database.sqlite.SQLiteConstraintException e){
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(),"Error al actualizar el alumno",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void rellenarCampos() {
        Alumno alumno = mainActivityViewModel.getAlumno();
        b.alumnoTxtName.setText(alumno.getNombre());
        b.alumnoTxtPhonenumber.setText(alumno.getTelefono());
        b.alumnoTxtEmail.setText(alumno.getEmail());

        ArrayAdapter cursoAdapter = (ArrayAdapter) b.spCurso.getAdapter();
        int posCurso = cursoAdapter.getPosition(alumno.getCurso());
        b.spCurso.setSelection(posCurso);

//        ArrayAdapter<String> empresaAdapter = (ArrayAdapter<String>) b.spEmpresa.getAdapter();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+adaptadorEmpresas);
//        int posEmp = empresaAdapter.getPosition(alumno.getIdEmpresa().toString());
//        b.spEmpresa.setSelection(posEmp);
//        b.spEmpresa.setSelection();

        b.alumnoTxtNameTutor.setText(alumno.getTutor().getNombre());
        b.alumnoTxtPhonenumberTutor.setText(alumno.getTutor().getTelefono());

        ArrayAdapter horarioTutorAdapter = (ArrayAdapter) b.alumnoSpHorarioTutor.getAdapter();
        int posHor = horarioTutorAdapter.getPosition(alumno.getTutor().getHorario());
        b.alumnoSpHorarioTutor.setSelection(posHor);
    }

    public void mostrarTecladoVirtual(View view){
        if(view != null && view.requestFocus()){
            InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
    private boolean validateTutorName() {
        if (b.alumnoTxtNameTutor.getText().toString().isEmpty()) {
            b.alumnoTxtNameTutor.setError(getString(R.string.main_invalid_data));
            b.alumnoTxtNameTutor.setEnabled(false);
            return false;
        }
        b.alumnoLblNameTutor.setEnabled(true);
        return true;
    }

    private boolean validateTutorPhonenumber() {
        if (!isValidPhone(b.alumnoTxtPhonenumberTutor.getText().toString())) {
            b.alumnoTxtPhonenumberTutor.setError(getString(R.string.main_invalid_data));
            b.alumnoImgPhonenumberTutor.setEnabled(false);
            b.alumnoLblPhoneTutor.setEnabled(false);
            return false;
        } else {
            b.alumnoImgPhonenumberTutor.setEnabled(true);
            b.alumnoLblPhoneTutor.setEnabled(true);
            return true;
        }
    }

    private boolean validateName() {
        if (b.alumnoTxtName.getText().toString().isEmpty()) {
            b.alumnoTxtName.setError(getString(R.string.main_invalid_data));
            b.alumnoLblName.setEnabled(false);
            return false;
        }
        b.alumnoLblName.setEnabled(true);
        return true;
    }

    private boolean validatePhonenumber() {
        if (!isValidPhone(b.alumnoTxtPhonenumber.getText().toString())) {
            b.alumnoTxtPhonenumber.setError(getString(R.string.main_invalid_data));
            b.alumnoImgPhonenumber.setEnabled(false);
            b.alumnoLblPhonenumber.setEnabled(false);
            return false;
        } else {
            b.alumnoImgPhonenumber.setEnabled(true);
            b.alumnoLblPhonenumber.setEnabled(true);
            return true;
        }
    }

    private boolean validateEmail() {
        if (!isValidEmail(b.alumnoTxtEmail.getText().toString())) {
            b.alumnoTxtEmail.setError(getString(R.string.main_invalid_data));
            b.alumnoImgEmail.setEnabled(false);
            b.alumnoLblEmail.setEnabled(false);
            return false;
        } else {
            b.alumnoImgEmail.setEnabled(true);
            b.alumnoLblEmail.setEnabled(true);
            return true;
        }
    }
}
