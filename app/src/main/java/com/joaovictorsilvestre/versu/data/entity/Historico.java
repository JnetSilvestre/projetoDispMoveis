package com.joaovictorsilvestre.versu.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entidade Room que representa um registro no histórico de sorteios.
 * Relacionamento: N Historico → 1 Versiculo (FK com CASCADE DELETE)
 */
@Entity(
    tableName = "historico",
    foreignKeys = @ForeignKey(
        entity = Versiculo.class,
        parentColumns = "id",
        childColumns = "versiculo_id",
        onDelete = ForeignKey.CASCADE
    ),
    indices = @Index(value = "versiculo_id")
)
public class Historico {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "versiculo_id")
    private int versiculoId;

    @ColumnInfo(name = "data_sorteio")
    private long dataSorteio;

    public Historico() {}

    public Historico(int versiculoId, long dataSorteio) {
        this.versiculoId = versiculoId;
        this.dataSorteio = dataSorteio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVersiculoId() { return versiculoId; }
    public void setVersiculoId(int versiculoId) { this.versiculoId = versiculoId; }

    public long getDataSorteio() { return dataSorteio; }
    public void setDataSorteio(long dataSorteio) { this.dataSorteio = dataSorteio; }
}
