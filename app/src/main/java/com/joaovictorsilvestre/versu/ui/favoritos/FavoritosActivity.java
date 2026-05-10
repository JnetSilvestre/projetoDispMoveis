package com.joaovictorsilvestre.versu.ui.favoritos;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joaovictorsilvestre.versu.R;

public class FavoritosActivity extends AppCompatActivity {

    private FavoritosViewModel viewModel;
    private FavoritosAdapter   adapter;
    private TextView           tvVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar toolbar = findViewById(R.id.toolbar_favoritos);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Favoritos");
        }

        tvVazio = findViewById(R.id.tv_favoritos_vazio);
        RecyclerView rv = findViewById(R.id.rv_favoritos);

        adapter = new FavoritosAdapter(v -> viewModel.removerFavorito(v));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FavoritosViewModel.class);
        viewModel.getFavoritos().observe(this, lista -> {
            adapter.setLista(lista);
            tvVazio.setVisibility(lista == null || lista.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
