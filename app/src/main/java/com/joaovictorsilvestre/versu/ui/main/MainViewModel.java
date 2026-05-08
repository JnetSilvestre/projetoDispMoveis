package com.joaovictorsilvestre.versu.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import com.joaovictorsilvestre.versu.data.repository.VersiculoRepository;

import java.util.List;

/**
 * ViewModel da tela principal.
 * Sobrevive a rotações de tela e expõe os dados via LiveData.
 */
public class MainViewModel extends AndroidViewModel {

    private final VersiculoRepository repository;

    private final MutableLiveData<Versiculo> versiculoAtual = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading       = new MutableLiveData<>(false);
    private final MutableLiveData<List<String>> categorias = new MutableLiveData<>();

    /** Categoria selecionada no filtro; null = todas as categorias. */
    private String categoriaFiltro = null;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new VersiculoRepository(application);
        carregarCategorias();
    }

    // ── Sorteio ──────────────────────────────────────────────────────────

    public void sortear() {
        isLoading.postValue(true);
        repository.sortearVersiculo(categoriaFiltro, versiculo -> {
            versiculoAtual.postValue(versiculo);
            isLoading.postValue(false);
        });
    }

    // ── Filtro de categoria ───────────────────────────────────────────────

    public void setCategoriaFiltro(String categoria) {
        this.categoriaFiltro = categoria;
    }

    private void carregarCategorias() {
        repository.listarCategorias(lista -> categorias.postValue(lista));
    }

    // ── Favorito ─────────────────────────────────────────────────────────

    /**
     * Inverte o estado de favorito do versículo atual.
     * Atualiza o banco em background e notifica a UI com o novo estado.
     */
    public void toggleFavorito() {
        Versiculo atual = versiculoAtual.getValue();
        if (atual == null) return;

        // Inverte localmente para atualização imediata da UI
        atual.setFavorito(!atual.isFavorito());
        versiculoAtual.postValue(atual);

        // Persiste no banco (a entidade já foi modificada acima)
        repository.toggleFavorito(atual);
    }

    // ── Getters ───────────────────────────────────────────────────────────

    public LiveData<Versiculo> getVersiculoAtual() { return versiculoAtual; }
    public LiveData<Boolean> getIsLoading()        { return isLoading; }
    public LiveData<List<String>> getCategorias()  { return categorias; }
}
