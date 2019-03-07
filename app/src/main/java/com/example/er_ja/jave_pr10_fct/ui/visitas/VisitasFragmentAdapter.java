package com.example.er_ja.jave_pr10_fct.ui.visitas;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.er_ja.jave_pr10_fct.R;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;
import com.example.er_ja.jave_pr10_fct.ui.EditInterface;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class VisitasFragmentAdapter extends ListAdapter<Visita, VisitasFragmentAdapter.ViewHolder> {

    private final EditInterface editInterface;

    public VisitasFragmentAdapter(EditInterface editInterface) {
        super(new DiffUtil.ItemCallback<Visita>() {
            @Override
            public boolean areItemsTheSame(@NonNull Visita oldItem, @NonNull Visita newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Visita oldItem, @NonNull Visita newItem) {
                return TextUtils.equals(oldItem.getDia(), newItem.getDia()) &&
                        TextUtils.equals(oldItem.getHoraInicio(), newItem.getHoraInicio()) &&
                        TextUtils.equals(oldItem.getHoraFin(), newItem.getHoraFin()) &&
                        TextUtils.equals(oldItem.getComentario(), newItem.getComentario()) &&
                        oldItem.getIdAlumno() == newItem.getIdAlumno();
            }
        });
        this.editInterface = editInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.visita_item, parent, false), editInterface);
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
    protected Visita getItem(int position) {
        return super.getItem(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView lblDia, lblHoraInicio, lblHoraFin, lblAlumno;
        private final Button btnEdit;

        public ViewHolder(@NonNull View itemView, EditInterface editInterface) {
            super(itemView);
            lblDia = ViewCompat.requireViewById(itemView, R.id.visita_item_lblDia);
            lblHoraInicio = ViewCompat.requireViewById(itemView, R.id.visita_item_lblHoraInicio);
            lblHoraFin = ViewCompat.requireViewById(itemView, R.id.visita_item_lblHoraFin);
            lblAlumno = ViewCompat.requireViewById(itemView, R.id.visita_item_lblAlumno);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.visita_item_btnEdit);
            btnEdit.setOnClickListener(v -> editInterface.onBtnEditClick(v,ViewHolder.this.getAdapterPosition()));
        }

        void bind(Visita visita){
            lblDia.setText(visita.getDia());
            lblHoraInicio.setText(visita.getHoraInicio());
            lblHoraFin.setText(visita.getHoraFin());
            lblAlumno.setText(visita.getNombreAlumno());
        }
    }
}
