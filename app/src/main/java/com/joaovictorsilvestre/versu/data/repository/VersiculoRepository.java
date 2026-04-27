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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Repositório que abstrai o acesso ao banco de dados para a camada de UI.
 * Toda operação de banco é executada em thread de background via ExecutorService,
 * nunca bloqueando a thread principal (UI thread).
 */
public class VersiculoRepository {

    private final VersiculoDao versiculoDao;
    private final HistoricoDao historicoDao;
    private final ExecutorService executor;

    // ── Callback para retorno assíncrono de versículo ─────────────────────
    public interface VersiculoCallback {
        void onResult(Versiculo versiculo);
    }

    public VersiculoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        versiculoDao = db.versiculoDao();
        historicoDao = db.historicoDao();
        executor = Executors.newSingleThreadExecutor();
    }

    // ── Sorteio ───────────────────────────────────────────────────────────

    /**
     * Sorteia um versículo aleatório (de qualquer categoria, ou de uma específica),
     * registra no histórico e retorna o resultado via callback na thread de background.
     * O ViewModel deve usar postValue() para atualizar o LiveData na UI thread.
     *
     * @param categoria Categoria para filtro, ou null para sortear de todas.
     * @param callback  Chamado com o versículo sorteado (pode ser null se banco vazio).
     */
    public void sortearVersiculo(String categoria, VersiculoCallback callback) {
        executor.execute(() -> {
            Versiculo versiculo;

            if (categoria == null || categoria.isEmpty()) {
                versiculo = versiculoDao.sortearAleatorio();
            } else {
                versiculo = versiculoDao.sortearPorCategoria(categoria);
            }

            if (versiculo != null) {
                Historico historico = new Historico(
                        versiculo.getId(),
                        System.currentTimeMillis()
                );
                historicoDao.inserir(historico);
            }

            if (callback != null) {
                callback.onResult(versiculo);
            }
        });
    }

    // ── Categorias ────────────────────────────────────────────────────────

    public void listarCategorias(CategoriaCallback callback) {
        executor.execute(() -> {
            List<String> categorias = versiculoDao.listarCategorias();
            if (callback != null) callback.onResult(categorias);
        });
    }

    public interface CategoriaCallback {
        void onResult(List<String> categorias);
    }

    // ── Favoritos ─────────────────────────────────────────────────────────

    public LiveData<List<Versiculo>> getFavoritos() {
        return versiculoDao.listarFavoritos();
    }

    public void toggleFavorito(Versiculo versiculo) {
        executor.execute(() -> {
            versiculo.setFavorito(!versiculo.isFavorito());
            versiculoDao.atualizar(versiculo);
        });
    }

    // ── Histórico ─────────────────────────────────────────────────────────

    public LiveData<List<HistoricoComVersiculo>> getHistorico() {
        return historicoDao.listarTodos();
    }

    public void deletarHistorico(Historico historico) {
        executor.execute(() -> historicoDao.deletar(historico));
    }

    public void limparHistorico() {
        executor.execute(historicoDao::limparTudo);
    }
}
