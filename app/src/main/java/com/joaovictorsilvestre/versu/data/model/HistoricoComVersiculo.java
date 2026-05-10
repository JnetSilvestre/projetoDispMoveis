package com.joaovictorsilvestre.versu.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;

/** POJO Room — une Historico + Versiculo em uma única query @Transaction */
public class HistoricoComVersiculo {

    @Embedded
    public Historico historico;

    @Relation(parentColumn = "versiculo_id", entityColumn = "id")
    public Versiculo versiculo;
}
