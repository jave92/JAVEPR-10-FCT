package com.example.er_ja.jave_pr10_fct.ui.main;

import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private Alumno alumno = null;
    private Empresa empresa = null;
    private Visita visitaUpdate = null;
    private Visita visitaInsert = null;

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

    public Visita getVisitaUpdate() {
        return visitaUpdate;
    }

    public void setVisitaUpdate(Visita visita) {
        this.visitaUpdate = visita;
    }

    public Visita getVisitaInsert() {
        return visitaInsert;
    }

    public void setVisitaInsert(Visita visitaInsert) {
        this.visitaInsert = visitaInsert;
    }
}
