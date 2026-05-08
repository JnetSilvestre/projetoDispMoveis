package com.joaovictorsilvestre.versu.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;

/**
 * POJO que combina Historico + Versiculo para consultas Room com @Transaction.
 */
public class HistoricoComVersiculo {

    @Embedded
    public Historico historico;

    @Relation(
        parentColumn = "versiculo_id",
        entityColumn = "id"
    )
    public Versiculo versiculo;
}
