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

/**
 * Repositório — abstrai o acesso ao banco para o ViewModel.
 * Todas as operações de escrita/leitura bloqueante rodam no executor do AppDatabase.
 */
public class VersiculoRepository {

    private final VersiculoDao versiculoDao;
    private final HistoricoDao historicoDao;

    public interface VersiculoCallback {
        void onResult(Versiculo versiculo);
    }

    public interface CategoriaCallback {
        void onResult(List<String> categorias);
    }

    public VersiculoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        versiculoDao = db.versiculoDao();
        historicoDao = db.historicoDao();
    }

    // ── Sorteio ──────────────────────────────────────────────────────────

    public void sortearVersiculo(String categoria, VersiculoCallback callback) {
        AppDatabase.executor.execute(() -> {
            Versiculo v = (categoria == null || categoria.isEmpty())
                    ? versiculoDao.sortearAleatorio()
                    : versiculoDao.sortearPorCategoria(categoria);

            if (v != null) {
                historicoDao.inserir(new Historico(v.getId(), System.currentTimeMillis()));
            }
            if (callback != null) callback.onResult(v);
        });
    }

    // ── Categorias ────────────────────────────────────────────────────────

    public void listarCategorias(CategoriaCallback callback) {
        AppDatabase.executor.execute(() -> {
            if (callback != null) callback.onResult(versiculoDao.listarCategorias());
        });
    }

    // ── Favoritos ────────────────────────────────────────────────────────

    public LiveData<List<Versiculo>> getFavoritos() {
        return versiculoDao.listarFavoritos();
    }

    public void toggleFavorito(Versiculo versiculo) {
        AppDatabase.executor.execute(() -> {
            versiculo.setFavorito(!versiculo.isFavorito());
            versiculoDao.atualizar(versiculo);
        });
    }

    // ── Histórico ─────────────────────────────────────────────────────────

    public LiveData<List<HistoricoComVersiculo>> getHistorico() {
        return historicoDao.listarTodos();
    }

    public void deletarHistorico(Historico historico) {
        AppDatabase.executor.execute(() -> historicoDao.deletar(historico));
    }

    public void limparHistorico() {
        AppDatabase.executor.execute(historicoDao::limparTudo);
    }
}
