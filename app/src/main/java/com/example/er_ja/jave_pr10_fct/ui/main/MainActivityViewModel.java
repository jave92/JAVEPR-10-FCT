package com.example.er_ja.jave_pr10_fct.ui.main;

import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private Alumno alumno = null;
    private Empresa empresa = null;
    private Visita visita = null;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}
