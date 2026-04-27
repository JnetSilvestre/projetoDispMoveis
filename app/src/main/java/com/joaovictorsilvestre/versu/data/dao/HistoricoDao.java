package com.joaovictorsilvestre.versu.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.model.HistoricoComVersiculo;

import java.util.List;

/**
 * DAO (Data Access Object) para operações na tabela de histórico.
 */
@Dao
public interface HistoricoDao {

    /** Registra um novo sorteio no histórico. */
    @Insert
    void inserir(Historico historico);

    /** Remove uma entrada específica do histórico. */
    @Delete
    void deletar(Historico historico);

    /** Remove todo o histórico de sorteios. */
    @Query("DELETE FROM historico")
    void limparTudo();

    /**
     * Retorna todos os registros do histórico junto com o versículo correspondente.
     * Ordenado do mais recente ao mais antigo. Observável via LiveData.
     */
    @Transaction
    @Query("SELECT * FROM historico ORDER BY data_sorteio DESC")
    LiveData<List<HistoricoComVersiculo>> listarTodos();

    /** Retorna o total de registros no histórico. */
    @Query("SELECT COUNT(*) FROM historico")
    int contar();
}
