package com.joaovictorsilvestre.versu.ui.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.joaovictorsilvestre.versu.R;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity principal do aplicativo VersU.
 * Exibe o versículo sorteado e gerencia as interações do usuário:
 * sorteio aleatório, filtro por categoria e marcação de favorito.
 */
public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    // Views
    private CardView cardVersiculo;
    private TextView tvTextoVersiculo;
    private TextView tvReferenciaVersiculo;
    private TextView tvCategoriaVersiculo;
    private TextView tvPlaceholder;
    private MaterialButton btnSortear;
    private ImageButton btnFavorito;
    private ChipGroup chipGroupCategorias;

    // Animações
    private Animation animFlipIn;
    private Animation animFlipOut;
    private Animation animPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        loadAnimations();
        setupViewModel();
    }

    // ── Bind Views ───────────────────────────────────────────────────────

    private void bindViews() {
        cardVersiculo      = findViewById(R.id.card_versiculo);
        tvTextoVersiculo   = findViewById(R.id.tv_texto_versiculo);
        tvReferenciaVersiculo = findViewById(R.id.tv_referencia_versiculo);
        tvCategoriaVersiculo  = findViewById(R.id.tv_categoria_versiculo);
        tvPlaceholder      = findViewById(R.id.tv_placeholder);
        btnSortear         = findViewById(R.id.btn_sortear);
        btnFavorito        = findViewById(R.id.btn_favorito);
        chipGroupCategorias = findViewById(R.id.chip_group_categorias);

        btnSortear.setOnClickListener(v -> viewModel.sortear());
        btnFavorito.setOnClickListener(v -> {
            Versiculo atual = viewModel.getVersiculoAtual().getValue();
            if (atual != null) viewModel.toggleFavorito(atual);
        });
    }

    // ── Animações ────────────────────────────────────────────────────────

    private void loadAnimations() {
        animFlipIn  = AnimationUtils.loadAnimation(this, R.anim.flip_card_in);
        animFlipOut = AnimationUtils.loadAnimation(this, R.anim.flip_card_out);
        animPulse   = AnimationUtils.loadAnimation(this, R.anim.pulse);
    }

    // ── ViewModel ────────────────────────────────────────────────────────

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observa versículo sorteado
        viewModel.getVersiculoAtual().observe(this, versiculo -> {
            if (versiculo != null) {
                exibirVersiculoComAnimacao(versiculo);
            }
        });

        // Observa estado de loading — desabilita botão durante sorteio
        viewModel.getIsLoading().observe(this, loading -> {
            btnSortear.setEnabled(!loading);
            btnSortear.setAlpha(loading ? 0.6f : 1.0f);
        });

        // Observa lista de categorias e monta os Chips
        viewModel.getCategorias().observe(this, this::montarChips);
    }

    // ── Chips de Categoria ───────────────────────────────────────────────

    private void montarChips(List<String> categorias) {
        chipGroupCategorias.removeAllViews();

        // Chip "Todas"
        Chip chipTodas = new Chip(this);
        chipTodas.setText("Todas");
        chipTodas.setCheckable(true);
        chipTodas.setChecked(true);
        chipTodas.setChipBackgroundColorResource(R.color.chip_background_selector);
        chipTodas.setTextColor(getResources().getColorStateList(R.color.chip_text_selector, getTheme()));
        chipTodas.setOnCheckedChangeListener((chip, isChecked) -> {
            if (isChecked) viewModel.setCategoriaFiltro(null);
        });
        chipGroupCategorias.addView(chipTodas);

        if (categorias == null) return;

        for (String cat : categorias) {
            Chip chip = new Chip(this);
            chip.setText(cat);
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(R.color.chip_background_selector);
            chip.setTextColor(getResources().getColorStateList(R.color.chip_text_selector, getTheme()));
            chip.setOnCheckedChangeListener((c, isChecked) -> {
                if (isChecked) viewModel.setCategoriaFiltro(cat);
            });
            chipGroupCategorias.addView(chip);
        }
    }

    // ── Exibição do Versículo ─────────────────────────────────────────────

    private void exibirVersiculoComAnimacao(Versiculo versiculo) {
        // Esconde placeholder se for o primeiro sorteio
        tvPlaceholder.setVisibility(View.GONE);

        // Anima saída do card atual (flip out)
        animFlipOut.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation a) {}
            @Override public void onAnimationRepeat(Animation a) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Atualiza conteúdo enquanto card está "virado"
                tvTextoVersiculo.setText(versiculo.getTexto());
                tvReferenciaVersiculo.setText(versiculo.getReferencia());
                tvCategoriaVersiculo.setText(versiculo.getCategoria());

                // Ícone de favorito
                btnFavorito.setImageResource(
                    versiculo.isFavorito()
                        ? R.drawable.ic_favorite_filled
                        : R.drawable.ic_favorite_border
                );
                btnFavorito.setVisibility(View.VISIBLE);

                // Anima entrada do novo card (flip in)
                cardVersiculo.startAnimation(animFlipIn);
            }
        });

        cardVersiculo.startAnimation(animFlipOut);
        btnSortear.startAnimation(animPulse);
    }
}
