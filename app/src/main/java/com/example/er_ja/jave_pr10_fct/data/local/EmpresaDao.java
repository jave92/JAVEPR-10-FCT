package com.example.er_ja.jave_pr10_fct.data.local;

        import com.example.er_ja.jave_pr10_fct.data.local.entity.Empresa;

        import java.util.List;

        import androidx.lifecycle.LiveData;
        import androidx.room.Dao;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.Query;
        import androidx.room.Update;

@Dao
public interface EmpresaDao {
    @Insert
    void insert(Empresa empresa);
    @Update
    void update(Empresa empresa);
    @Delete
    void delete(Empresa empresa);
    @Query("SELECT * FROM empresa ORDER BY nombre")
    LiveData<List<Empresa>> getEmpresas();
    @Query("SELECT nombre FROM empresa ORDER BY nombre")
    LiveData<List<String>> getNombreEmpresas();
    @Query("SELECT * FROM empresa WHERE nombre=:nombre")
    Empresa getEmpresa(String nombre);
    @Query("SELECT nombre FROM empresa WHERE id=:id")
    String getNombreEmpresa(Long id);
}
