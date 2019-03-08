package com.example.er_ja.jave_pr10_fct.data;

import android.app.Application;
import android.os.AsyncTask;

import com.example.er_ja.jave_pr10_fct.data.local.AlumnoDao;
import com.example.er_ja.jave_pr10_fct.data.local.Database;
import com.example.er_ja.jave_pr10_fct.data.local.EmpresaDao;
import com.example.er_ja.jave_pr10_fct.data.local.VisitaDao;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Alumno;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;
import com.example.er_ja.jave_pr10_fct.data.local.entity.Visita;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {
    private final AlumnoDao alumnoDao;
    private final EmpresaDao empresaDao;
    private final VisitaDao visitaDao;

    public RepositoryImpl(Application application) {
        Database db = Database.getInstance(application);
        alumnoDao = db.alumnoDao();
        empresaDao = db.empresaDao();
        visitaDao = db.visitaDao();
    }

    @Override
    public LiveData<List<Alumno>> getAlumnos() {
        return alumnoDao.getAlumnos();
    }

    @Override
    public void insertAlumno(Alumno alumno) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->alumnoDao.insert(alumno));
    }

    @Override
    public void updateAlumno(Alumno alumno) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->alumnoDao.update(alumno));
    }

    @Override
    public void deleteAlumno(Alumno alumno) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->alumnoDao.delete(alumno));
    }

    @Override
    public Alumno getAlumno(String nombre) {
        return alumnoDao.getAlumno(nombre);
    }

    @Override
    public String getNombreAlumno(Long id) {
        return alumnoDao.getNombreAlumno(id);
    }

    @Override
    public LiveData<List<Empresa>> getEmpresas() {
        return empresaDao.getEmpresas();
    }

    @Override
    public LiveData<List<String>> getNombreEmpresas() {
        return empresaDao.getNombreEmpresas();
    }

    @Override
    public void insertEmpresa(Empresa empresa) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->empresaDao.insert(empresa));
    }

    @Override
    public void updateEmpresa(Empresa empresa) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->empresaDao.update(empresa));
    }

    @Override
    public void deleteEmpresa(Empresa empresa) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->empresaDao.delete(empresa));
    }

    @Override
    public Empresa getEmpresa(String nombre) {
        return empresaDao.getEmpresa(nombre);

    }

    @Override
    public String getNombreEmpresa(Long id) {
        return empresaDao.getNombreEmpresa(id);
    }

    @Override
    public LiveData<List<Visita>> getVisitas() {
        return visitaDao.getVisitas();
    }

    @Override
    public LiveData<List<String>> getNombreAlumnos() {
        return alumnoDao.getNombreAlumnos();
    }

    @Override
    public void insertVisita(Visita visita) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->visitaDao.insert(visita));
    }

    @Override
    public void updateVisita(Visita visita) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->visitaDao.update(visita));
    }

    @Override
    public void deleteVisita(Visita visita) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->visitaDao.delete(visita));

    }
}
