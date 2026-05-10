package com.joaovictorsilvestre.versu.ui.favoritos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import com.joaovictorsilvestre.versu.data.repository.VersiculoRepository;

import java.util.List;

public class FavoritosViewModel extends AndroidViewModel {

    private final VersiculoRepository repo;
    private final LiveData<List<Versiculo>> favoritos;

    public FavoritosViewModel(@NonNull Application app) {
        super(app);
        repo      = new VersiculoRepository(app);
        favoritos = repo.getFavoritos();
    }

    public LiveData<List<Versiculo>> getFavoritos() { return favoritos; }

    public void removerFavorito(Versiculo v) { repo.setFavorito(v, false); }
}
