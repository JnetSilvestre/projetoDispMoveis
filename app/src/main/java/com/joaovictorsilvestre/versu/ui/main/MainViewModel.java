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
 * Sobrevive a rotações de tela e mantém o versículo atual e o estado de loading.
 */
public class MainViewModel extends AndroidViewModel {

    private final VersiculoRepository repository;

    private final MutableLiveData<Versiculo> versiculoAtual = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> categoriasSelecionada = new MutableLiveData<>(null);
    private final MutableLiveData<List<String>> categorias = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new VersiculoRepository(application);
        carregarCategorias();
    }

    // ── Sortear ──────────────────────────────────────────────────────────

    public void sortear() {
        isLoading.postValue(true);
        String categoria = categoriasSelecionada.getValue();
        repository.sortearVersiculo(categoria, versiculo -> {
            versiculoAtual.postValue(versiculo);
            isLoading.postValue(false);
        });
    }

    // ── Categorias ───────────────────────────────────────────────────────

    private void carregarCategorias() {
        repository.listarCategorias(lista -> categorias.postValue(lista));
    }

    public void setCategoriaFiltro(String categoria) {
        categoriasSelecionada.setValue(categoria);
    }

    // ── Favorito ─────────────────────────────────────────────────────────

    public void toggleFavorito(Versiculo versiculo) {
        repository.toggleFavorito(versiculo);
        // Atualiza o LiveData para refletir o novo estado na UI
        versiculo.setFavorito(!versiculo.isFavorito());
        versiculoAtual.postValue(versiculo);
    }

    // ── Getters LiveData ─────────────────────────────────────────────────

    public LiveData<Versiculo> getVersiculoAtual() { return versiculoAtual; }
    public LiveData<Boolean> getIsLoading() { return isLoading; }
    public LiveData<List<String>> getCategorias() { return categorias; }
    public LiveData<String> getCategoriasSelecionada() { return categoriasSelecionada; }
}
