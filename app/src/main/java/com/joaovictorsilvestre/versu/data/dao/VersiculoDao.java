package com.joaovictorsilvestre.versu.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joaovictorsilvestre.versu.data.entity.Versiculo;

import java.util.List;

/**
 * DAO (Data Access Object) para operações na tabela de versículos.
 */
@Dao
public interface VersiculoDao {

    /** Insere uma lista de versículos (usado na carga inicial do banco). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserirTodos(List<Versiculo> versiculos);

    /** Sorteia um versículo aleatório de qualquer categoria. */
    @Query("SELECT * FROM versiculos ORDER BY RANDOM() LIMIT 1")
    Versiculo sortearAleatorio();

    /** Sorteia um versículo aleatório de uma categoria específica. */
    @Query("SELECT * FROM versiculos WHERE categoria = :categoria ORDER BY RANDOM() LIMIT 1")
    Versiculo sortearPorCategoria(String categoria);

    /** Retorna lista distinta de categorias existentes. */
    @Query("SELECT DISTINCT categoria FROM versiculos ORDER BY categoria ASC")
    List<String> listarCategorias();

    /** Retorna todos os versículos marcados como favoritos (observável). */
    @Query("SELECT * FROM versiculos WHERE favorito = 1 ORDER BY referencia ASC")
    LiveData<List<Versiculo>> listarFavoritos();

    /** Atualiza um versículo (usado para marcar/desmarcar favorito). */
    @Update
    void atualizar(Versiculo versiculo);

    /** Retorna o total de versículos no banco. */
    @Query("SELECT COUNT(*) FROM versiculos")
    int contar();
}
