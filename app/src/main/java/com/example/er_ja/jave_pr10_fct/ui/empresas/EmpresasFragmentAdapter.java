package com.example.er_ja.jave_pr10_fct.ui.empresas;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.ui.EditInterface;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class EmpresasFragmentAdapter extends ListAdapter<Empresa, EmpresasFragmentAdapter.ViewHolder> {

    private final EditInterface editInterface;

    public EmpresasFragmentAdapter(EditInterface editInterface) {
        super(new DiffUtil.ItemCallback<Empresa>() {
            @Override
            public boolean areItemsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
                return TextUtils.equals(oldItem.getNombre(), newItem.getNombre()) &&
                        TextUtils.equals(oldItem.getCIF(), newItem.getCIF()) &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        TextUtils.equals(oldItem.getTelefono(), newItem.getTelefono()) &&
                        TextUtils.equals(oldItem.getDireccion(), newItem.getDireccion()) &&
                        TextUtils.equals(oldItem.getNombreContacto(), newItem.getNombreContacto());
            }
        });
        this.editInterface = editInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empresa_item, parent, false), editInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    protected Empresa getItem(int position) {
        return super.getItem(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView lblName, lblEmail, lblPhone, lblCIF, lblAddress,lblContact;
        private final Button btnEdit;

        public ViewHolder(@NonNull View itemView, EditInterface editInterface) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.empresa_item_lblName);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.empresa_item_lblEmail);
            lblPhone = ViewCompat.requireViewById(itemView, R.id.empresa_item_lblPhone);
            lblCIF = ViewCompat.requireViewById(itemView, R.id.empresa_item_lblCIF);
            lblAddress = ViewCompat.requireViewById(itemView, R.id.empresa_item_lblAddress);
            lblContact = ViewCompat.requireViewById(itemView, R.id.empresa_item_lblContacto);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.empresa_item_btnEdit);
            btnEdit.setOnClickListener(v -> editInterface.onBtnEditClick(v,ViewHolder.this.getAdapterPosition()));
        }

        void bind(Empresa empresa){
            lblName.setText(empresa.getNombre());
            lblEmail.setText(empresa.getEmail());
            lblPhone.setText(empresa.getTelefono());
            lblCIF.setText(empresa.getCIF());
            lblAddress.setText(empresa.getDireccion());
            lblContact.setText(empresa.getNombreContacto());
        }
    }
}
