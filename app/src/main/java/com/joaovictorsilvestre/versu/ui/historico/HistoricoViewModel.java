package com.joaovictorsilvestre.versu.ui.historico;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.model.HistoricoComVersiculo;
import com.joaovictorsilvestre.versu.data.repository.VersiculoRepository;

import java.util.List;

public class HistoricoViewModel extends AndroidViewModel {

    private final VersiculoRepository repo;
    private final LiveData<List<HistoricoComVersiculo>> historico;

    public HistoricoViewModel(@NonNull Application app) {
        super(app);
        repo      = new VersiculoRepository(app);
        historico = repo.getHistorico();
    }

    public LiveData<List<HistoricoComVersiculo>> getHistorico() { return historico; }

    public void deletar(Historico h) { repo.deletarHistorico(h); }

    public void limparTudo() { repo.limparHistorico(); }
}
