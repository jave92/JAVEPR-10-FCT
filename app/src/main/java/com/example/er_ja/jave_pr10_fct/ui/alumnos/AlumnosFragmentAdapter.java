package com.example.er_ja.jave_pr10_fct.ui.alumnos;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.ui.EditInterface;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class AlumnosFragmentAdapter extends ListAdapter<Alumno, AlumnosFragmentAdapter.ViewHolder> {

    private final EditInterface editInterface;

    public AlumnosFragmentAdapter(EditInterface editInterface) {
        super(new DiffUtil.ItemCallback<Alumno>() {
            @Override
            public boolean areItemsTheSame(@NonNull Alumno oldItem, @NonNull Alumno newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Alumno oldItem, @NonNull Alumno newItem) {
                return TextUtils.equals(oldItem.getNombre(), newItem.getNombre()) &&
                        TextUtils.equals(oldItem.getCurso(), newItem.getCurso()) &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        TextUtils.equals(oldItem.getTelefono(), newItem.getTelefono()) &&
                        oldItem.getIdEmpresa() == newItem.getIdEmpresa();
            }
        });
        this.editInterface = editInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_item, parent, false), editInterface);
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
    protected Alumno getItem(int position) {
        return super.getItem(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView lblName, lblEmail, lblPhone, lblCurso, lblEmpresa;
        private final Button btnEdit;

        public ViewHolder(@NonNull View itemView, EditInterface editInterface) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.alumno_item_lblName);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.alumno_item_lblEmail);
            lblPhone = ViewCompat.requireViewById(itemView, R.id.alumno_item_lblPhone);
            lblCurso = ViewCompat.requireViewById(itemView, R.id.alumno_item_lblCurso);
            lblEmpresa = ViewCompat.requireViewById(itemView, R.id.alumno_item_lblEmpresa);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.alumno_item_btnEdit);
            btnEdit.setOnClickListener(v -> editInterface.onBtnEditClick(v,ViewHolder.this.getAdapterPosition()));
        }

        void bind(Alumno alumno){
            lblName.setText(alumno.getNombre());
            lblEmail.setText(alumno.getEmail());
            lblPhone.setText(alumno.getTelefono());
            lblCurso.setText(alumno.getCurso());
            if(alumno.getIdEmpresa()!=null){
                lblEmpresa.setText(alumno.getNombreEmpresa());
            }

        }
    }
}
