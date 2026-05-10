package com.joaovictorsilvestre.versu.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.joaovictorsilvestre.versu.data.dao.HistoricoDao;
import com.joaovictorsilvestre.versu.data.dao.VersiculoDao;
import com.joaovictorsilvestre.versu.data.database.AppDatabase;
import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import com.joaovictorsilvestre.versu.data.model.HistoricoComVersiculo;

import java.util.List;

public class VersiculoRepository {

    private final VersiculoDao versiculoDao;
    private final HistoricoDao historicoDao;

    public interface VersiculoCallback { void onResult(Versiculo v); }
    public interface CategoriaCallback  { void onResult(List<String> cats); }

    public VersiculoRepository(Application app) {
        AppDatabase db = AppDatabase.getInstance(app);
        versiculoDao   = db.versiculoDao();
        historicoDao   = db.historicoDao();
    }

    // ── Sorteio ──────────────────────────────────────────────────────────
    public void sortear(String categoria, VersiculoCallback cb) {
        AppDatabase.executor.execute(() -> {
            Versiculo v = (categoria == null || categoria.isEmpty())
                    ? versiculoDao.sortearAleatorio()
                    : versiculoDao.sortearPorCategoria(categoria);
            if (v != null)
                historicoDao.inserir(new Historico(v.getId(), System.currentTimeMillis()));
            if (cb != null) cb.onResult(v);
        });
    }

    // ── Categorias ────────────────────────────────────────────────────────
    public void listarCategorias(CategoriaCallback cb) {
        AppDatabase.executor.execute(() -> {
            if (cb != null) cb.onResult(versiculoDao.listarCategorias());
        });
    }

    // ── Favorito ─────────────────────────────────────────────────────────
    public LiveData<List<Versiculo>> getFavoritos() {
        return versiculoDao.listarFavoritos();
    }

    public void setFavorito(Versiculo v, boolean favorito) {
        AppDatabase.executor.execute(() -> {
            v.setFavorito(favorito);
            versiculoDao.atualizar(v);
        });
    }

    // ── Histórico ─────────────────────────────────────────────────────────
    public LiveData<List<HistoricoComVersiculo>> getHistorico() {
        return historicoDao.listarTodos();
    }

    public void deletarHistorico(Historico h) {
        AppDatabase.executor.execute(() -> historicoDao.deletar(h));
    }

    public void limparHistorico() {
        AppDatabase.executor.execute(historicoDao::limparTudo);
    }
}
