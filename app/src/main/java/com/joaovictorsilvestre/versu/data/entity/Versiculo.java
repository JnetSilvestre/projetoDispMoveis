package com.joaovictorsilvestre.versu.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidade Room que representa um versículo bíblico.
 * Relacionamento: 1 Versiculo → N Historico
 */
@Entity(tableName = "versiculos")
public class Versiculo {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "texto")
    private String texto;

    @ColumnInfo(name = "referencia")
    private String referencia;

    @ColumnInfo(name = "categoria")
    private String categoria;

    @ColumnInfo(name = "favorito")
    private boolean favorito;

    public Versiculo(int id, String texto, String referencia, String categoria) {
        this.id = id;
        this.texto = texto;
        this.referencia = referencia;
        this.categoria = categoria;
        this.favorito = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isFavorito() { return favorito; }
    public void setFavorito(boolean favorito) { this.favorito = favorito; }
}
