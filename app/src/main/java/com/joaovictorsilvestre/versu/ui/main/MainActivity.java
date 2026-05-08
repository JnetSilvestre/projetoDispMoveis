package com.joaovictorsilvestre.versu.ui.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.joaovictorsilvestre.versu.R;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;

import java.util.List;

/**
 * Tela principal do VersU.
 * Responsabilidades: sortear versículo, filtrar por categoria, marcar favorito.
 */
public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    // Views
    private CardView    cardVersiculo;
    private TextView    tvPlaceholder;
    private TextView    tvTexto;
    private View        viewDivider;
    private TextView    tvReferencia;
    private Chip        chipCategoriaVersiculo;
    private ImageButton btnFavorito;
    private ChipGroup   chipGroupCategorias;
    private MaterialButton btnSortear;

    // Animações
    private Animation animFlipOut;
    private Animation animFlipIn;
    private Animation animPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        bindViews();
        loadAnimations();
        setupViewModel();
    }

    // ── Setup ─────────────────────────────────────────────────────────────

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("VersU");
        }
    }

    private void bindViews() {
        cardVersiculo          = findViewById(R.id.card_versiculo);
        tvPlaceholder          = findViewById(R.id.tv_placeholder);
        tvTexto                = findViewById(R.id.tv_texto_versiculo);
        viewDivider            = findViewById(R.id.view_divider);
        tvReferencia           = findViewById(R.id.tv_referencia);
        chipCategoriaVersiculo = findViewById(R.id.chip_categoria_versiculo);
        btnFavorito            = findViewById(R.id.btn_favorito);
        chipGroupCategorias    = findViewById(R.id.chip_group_categorias);
        btnSortear             = findViewById(R.id.btn_sortear);

        btnSortear.setOnClickListener(v -> viewModel.sortear());

        btnFavorito.setOnClickListener(v -> {
            viewModel.toggleFavorito();
        });
    }

    private void loadAnimations() {
        animFlipOut = AnimationUtils.loadAnimation(this, R.anim.card_flip_out);
        animFlipIn  = AnimationUtils.loadAnimation(this, R.anim.card_flip_in);
        animPulse   = AnimationUtils.loadAnimation(this, R.anim.btn_pulse);
    }

    // ── ViewModel ─────────────────────────────────────────────────────────

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Versículo sorteado
        viewModel.getVersiculoAtual().observe(this, this::exibirVersiculo);

        // Estado de loading
        viewModel.getIsLoading().observe(this, loading -> {
            btnSortear.setEnabled(!loading);
            btnSortear.setAlpha(loading ? 0.55f : 1.0f);
        });

        // Lista de categorias → monta os chips
        viewModel.getCategorias().observe(this, this::montarChips);
    }

    // ── Chips de categoria ────────────────────────────────────────────────

    private void montarChips(List<String> categorias) {
        chipGroupCategorias.removeAllViews();

        // Chip "Todas" — selecionado por padrão
        Chip chipTodas = criarChip("Todas", true);
        chipTodas.setOnCheckedChangeListener((chip, checked) -> {
            if (checked) viewModel.setCategoriaFiltro(null);
        });
        chipGroupCategorias.addView(chipTodas);

        if (categorias == null) return;

        for (String cat : categorias) {
            Chip chip = criarChip(cat, false);
            chip.setOnCheckedChangeListener((c, checked) -> {
                if (checked) viewModel.setCategoriaFiltro(cat);
            });
            chipGroupCategorias.addView(chip);
        }
    }

    private Chip criarChip(String texto, boolean selecionado) {
        Chip chip = new Chip(this);
        chip.setText(texto);
        chip.setCheckable(true);
        chip.setChecked(selecionado);
        chip.setChipBackgroundColorResource(R.color.chip_background_selector);
        chip.setTextColor(getResources().getColorStateList(
                R.color.chip_text_selector, getTheme()));
        return chip;
    }

    // ── Exibição do versículo ─────────────────────────────────────────────

    private void exibirVersiculo(Versiculo versiculo) {
        if (versiculo == null) {
            Toast.makeText(this,
                getString(R.string.erro_sem_versiculo),
                Toast.LENGTH_SHORT).show();
            return;
        }

        // Animação de saída → atualiza conteúdo → animação de entrada
        animFlipOut.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation a) {}
            @Override public void onAnimationRepeat(Animation a) {}

            @Override
            public void onAnimationEnd(Animation a) {
                // Atualiza conteúdo enquanto card está "virado"
                tvPlaceholder.setVisibility(View.GONE);

                tvTexto.setText(versiculo.getTexto());
                tvTexto.setVisibility(View.VISIBLE);

                viewDivider.setVisibility(View.VISIBLE);

                tvReferencia.setText(versiculo.getReferencia());
                tvReferencia.setVisibility(View.VISIBLE);

                chipCategoriaVersiculo.setText(versiculo.getCategoria());
                chipCategoriaVersiculo.setVisibility(View.VISIBLE);

                btnFavorito.setImageResource(versiculo.isFavorito()
                        ? R.drawable.ic_favorite_filled
                        : R.drawable.ic_favorite_border);
                btnFavorito.setVisibility(View.VISIBLE);

                // Anima entrada
                cardVersiculo.startAnimation(animFlipIn);
            }
        });

        cardVersiculo.startAnimation(animFlipOut);
        btnSortear.startAnimation(animPulse);
    }
}
