package com.example.er_ja.jave_pr10_fct.ui.empresa;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentEmpresaBinding;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import static com.example.er_ja.jave_pr10_fct.utils.ValidationUtils.isValidEmail;
import static com.example.er_ja.jave_pr10_fct.utils.ValidationUtils.isValidPhone;

public class EmpresaFragment extends Fragment {
    private FragmentEmpresaBinding b;
    private EmpresaFragmentViewModel vm;
    private MainActivityViewModel mainActivityViewModel;

    public EmpresaFragment() {
    }

    public static EmpresaFragment newInstance(){
        EmpresaFragment empresaFragment = new EmpresaFragment();
        return empresaFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentEmpresaBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(EmpresaFragment.this, new EmpresaFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(EmpresaFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        setupViews();
    }

    private void setupViews() {
        b.empresaTxtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.empresaLblName.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.empresaLblName.setTypeface(Typeface.DEFAULT);
            }
        });

        b.empresaTxtName.addTextChangedListener(new TextWatcher() {
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

        b.empresaTxtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.empresaLblPhonenumber.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.empresaLblPhonenumber.setTypeface(Typeface.DEFAULT);
            }
        });

        b.empresaTxtPhonenumber.addTextChangedListener(new TextWatcher() {
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

        b.empresaTxtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.empresaLblEmail.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.empresaLblEmail.setTypeface(Typeface.DEFAULT);
            }
        });
        b.empresaTxtEmail.addTextChangedListener(new TextWatcher() {
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

        b.empresaTxtAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.empresaLblAddress.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.empresaLblAddress.setTypeface(Typeface.DEFAULT);
            }
        });

        b.empresaTxtCIF.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.empresaLblCIF.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.empresaLblCIF.setTypeface(Typeface.DEFAULT);
            }
        });

        b.empresaTxtContact.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                b.empresaLblContact.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                b.empresaLblContact.setTypeface(Typeface.DEFAULT);
            }
        });

        b.empresaTxtContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateNameContact();
            }
        });
        mostrarTecladoVirtual(b.empresaTxtName);
        if(mainActivityViewModel.getEmpresa()==null){
            b.empresaFab.setOnClickListener(v -> {
                try{
                    vm.insert(new Empresa(
                            b.empresaTxtName.getText().toString(),
                            b.empresaTxtCIF.getText().toString(),
                            b.empresaTxtAddress.getText().toString(),
                            b.empresaTxtPhonenumber.getText().toString(),
                            b.empresaTxtEmail.getText().toString(),
                            "urlLogo",
                            b.empresaTxtContact.getText().toString()));
                    Toast.makeText(getContext(),"Empresa guardada",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.empresasFragment);
                }catch (android.database.sqlite.SQLiteConstraintException e){
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(),"Error al guardar la empresa",Toast.LENGTH_SHORT).show();
                }

            });
        }else{
            rellenarCampos();
            b.empresaFab.setOnClickListener(v -> {
                try{
                    Empresa emp = mainActivityViewModel.getEmpresa();
                    emp.setNombre(b.empresaTxtName.getText().toString());
                    emp.setCIF(b.empresaTxtCIF.getText().toString());
                    emp.setDireccion(b.empresaTxtAddress.getText().toString());
                    emp.setTelefono(b.empresaTxtPhonenumber.getText().toString());
                    emp.setEmail(b.empresaTxtEmail.getText().toString());
                    emp.setUrlLogo("urlLogo");
                    emp.setNombreContacto(b.empresaTxtContact.getText().toString());
                    vm.update(emp);
                    Toast.makeText(getContext(),"Empresa actualizada",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.empresasFragment);
                }catch (android.database.sqlite.SQLiteConstraintException e){
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(),"Error al guardar la empresa",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void rellenarCampos() {
        Empresa emp = mainActivityViewModel.getEmpresa();
        b.empresaTxtName.setText(emp.getNombre());
        b.empresaTxtCIF.setText(emp.getCIF());
        b.empresaTxtAddress.setText(emp.getDireccion());
        b.empresaTxtPhonenumber.setText(emp.getTelefono());
        b.empresaTxtEmail.setText(emp.getEmail());
        b.empresaTxtContact.setText(emp.getNombreContacto());
    }

    public void mostrarTecladoVirtual(View view){
        if(view != null && view.requestFocus()){
            InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
    private boolean validateNameContact() {
        if (b.empresaTxtContact.getText().toString().isEmpty()) {
            b.empresaTxtContact.setError(getString(R.string.main_invalid_data));
            b.empresaLblContact.setEnabled(false);
            return false;
        }
        b.empresaLblContact.setEnabled(true);
        return true;
    }

    private boolean validateEmail() {
        if (!isValidEmail(b.empresaTxtEmail.getText().toString())) {
            b.empresaTxtEmail.setError(getString(R.string.main_invalid_data));
            b.empresaImgEmail.setEnabled(false);
            b.empresaLblEmail.setEnabled(false);
            return false;
        } else {
            b.empresaImgEmail.setEnabled(true);
            b.empresaLblEmail.setEnabled(true);
            return true;
        }
    }

    private boolean validatePhonenumber() {
        if (!isValidPhone(b.empresaTxtPhonenumber.getText().toString())) {
            b.empresaTxtPhonenumber.setError(getString(R.string.main_invalid_data));
            b.empresaImgPhonenumber.setEnabled(false);
            b.empresaLblPhonenumber.setEnabled(false);
            return false;
        } else {
            b.empresaImgPhonenumber.setEnabled(true);
            b.empresaLblPhonenumber.setEnabled(true);
            return true;
        }
    }

    private boolean validateName() {
        if (b.empresaTxtName.getText().toString().isEmpty()) {
            b.empresaTxtName.setError(getString(R.string.main_invalid_data));
            b.empresaLblName.setEnabled(false);
            return false;
        }
        b.empresaLblName.setEnabled(true);
        return true;
    }
}
