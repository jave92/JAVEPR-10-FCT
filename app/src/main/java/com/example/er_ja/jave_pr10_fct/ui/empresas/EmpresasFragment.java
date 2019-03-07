package com.example.er_ja.jave_pr10_fct.ui.empresas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.RepositoryImpl;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentEmpresasBinding;
import com.example.er_ja.jave_pr10_fct.databinding.FragmentEmpresasBinding;
import com.example.er_ja.jave_pr10_fct.ui.EditInterface;
import com.example.er_ja.jave_pr10_fct.ui.main.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class EmpresasFragment extends Fragment {
    EmpresasFragmentViewModel vm;
    FragmentEmpresasBinding b;

    private EmpresasFragmentAdapter listAdapter;
    private MainActivityViewModel mainActivityViewModel;

    public EmpresasFragment() {
    }

    public static EmpresasFragment newInstance(){
        return new EmpresasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentEmpresasBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(EmpresasFragment.this, new EmpresasFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(EmpresasFragmentViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        //TODO CUIDAO CON EL SETEMPRESA NULL
        mainActivityViewModel.setEmpresa(null);
        setupViews();
        observeEmpresas();
    }

    private void observeEmpresas() {
        vm.getEmpresas().observe(this, empresas -> {
            listAdapter.submitList(empresas);
        });
    }

    private void setupViews() {
        listAdapter = new EmpresasFragmentAdapter(new EditInterface() {
            @Override
            public void onBtnEditClick(View v, int position) {
                editUser(listAdapter.getItem(position));
                Navigation.findNavController(v).navigate(R.id.empresaFragment);
            }
        });
        b.lstEmpresas.setHasFixedSize(true);
        b.lstEmpresas.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.empresas_lstEmpresas_columns)));
        b.lstEmpresas.setItemAnimator(new DefaultItemAnimator());
        b.lstEmpresas.setAdapter(listAdapter);
        b.empresasFab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_empresasFragment_to_empresaFragment));

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
        itemTouchHelper.attachToRecyclerView(b.lstEmpresas);
    }
    private void editUser(Empresa empresa) {
        mainActivityViewModel.setEmpresa(empresa);
    }
}
