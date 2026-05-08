package com.joaovictorsilvestre.versu.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joaovictorsilvestre.versu.data.entity.Versiculo;

import java.util.List;

@Dao
public interface VersiculoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserirTodos(List<Versiculo> versiculos);

    @Query("SELECT * FROM versiculos ORDER BY RANDOM() LIMIT 1")
    Versiculo sortearAleatorio();

    @Query("SELECT * FROM versiculos WHERE categoria = :categoria ORDER BY RANDOM() LIMIT 1")
    Versiculo sortearPorCategoria(String categoria);

    @Query("SELECT DISTINCT categoria FROM versiculos ORDER BY categoria ASC")
    List<String> listarCategorias();

    @Query("SELECT * FROM versiculos WHERE favorito = 1 ORDER BY referencia ASC")
    LiveData<List<Versiculo>> listarFavoritos();

    @Update
    void atualizar(Versiculo versiculo);

    @Query("SELECT COUNT(*) FROM versiculos")
    int contar();
}
