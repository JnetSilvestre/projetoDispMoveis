package com.joaovictorsilvestre.versu.ui.historico;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joaovictorsilvestre.versu.R;

public class HistoricoActivity extends AppCompatActivity {

    private HistoricoViewModel viewModel;
    private HistoricoAdapter   adapter;
    private TextView           tvVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        Toolbar toolbar = findViewById(R.id.toolbar_historico);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Histórico");
        }

        tvVazio = findViewById(R.id.tv_historico_vazio);
        RecyclerView rv = findViewById(R.id.rv_historico);

        adapter = new HistoricoAdapter(hcv -> viewModel.deletar(hcv.historico));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(HistoricoViewModel.class);
        viewModel.getHistorico().observe(this, lista -> {
            adapter.setLista(lista);
            tvVazio.setVisibility(lista == null || lista.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_historico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_limpar_historico) {
            new AlertDialog.Builder(this)
                .setTitle("Limpar histórico")
                .setMessage("Deseja apagar todo o histórico de sorteios?")
                .setPositiveButton("Apagar", (d, w) -> viewModel.limparTudo())
                .setNegativeButton("Cancelar", null)
                .show();
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
