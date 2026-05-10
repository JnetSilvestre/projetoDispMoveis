package com.joaovictorsilvestre.versu.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import com.joaovictorsilvestre.versu.data.repository.VersiculoRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final VersiculoRepository repo;

    private final MutableLiveData<Versiculo>    versiculoAtual = new MutableLiveData<>();
    private final MutableLiveData<Boolean>      isLoading      = new MutableLiveData<>(false);
    private final MutableLiveData<List<String>> categorias     = new MutableLiveData<>();
    private String categoriaFiltro = null;

    public MainViewModel(@NonNull Application app) {
        super(app);
        repo = new VersiculoRepository(app);
        repo.listarCategorias(cats -> categorias.postValue(cats));
    }

    public void sortear() {
        isLoading.postValue(true);
        repo.sortear(categoriaFiltro, v -> {
            versiculoAtual.postValue(v);
            isLoading.postValue(false);
        });
    }

    public void setCategoriaFiltro(String cat) {
        categoriaFiltro = cat;
    }

    /** Inverte favorito: busca o estado atual do objeto e persiste o oposto. */
    public void toggleFavorito() {
        Versiculo atual = versiculoAtual.getValue();
        if (atual == null) return;
        boolean novoEstado = !atual.isFavorito();
        // Atualiza objeto local e notifica UI imediatamente
        atual.setFavorito(novoEstado);
        versiculoAtual.setValue(atual);
        // Persiste no banco
        repo.setFavorito(atual, novoEstado);
    }

    public LiveData<Versiculo>    getVersiculoAtual() { return versiculoAtual; }
    public LiveData<Boolean>      getIsLoading()      { return isLoading; }
    public LiveData<List<String>> getCategorias()     { return categorias; }
}
