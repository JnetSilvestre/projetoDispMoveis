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

@Dao
public interface HistoricoDao {

    @Insert
    void inserir(Historico historico);

    @Delete
    void deletar(Historico historico);

    @Query("DELETE FROM historico")
    void limparTudo();

    @Transaction
    @Query("SELECT * FROM historico ORDER BY data_sorteio DESC")
    LiveData<List<HistoricoComVersiculo>> listarTodos();

    @Query("SELECT COUNT(*) FROM historico")
    int contar();
}
