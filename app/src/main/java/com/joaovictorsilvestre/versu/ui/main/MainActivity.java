package com.joaovictorsilvestre.versu.ui.main;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.joaovictorsilvestre.versu.R;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import com.joaovictorsilvestre.versu.ui.favoritos.FavoritosActivity;
import com.joaovictorsilvestre.versu.ui.historico.HistoricoActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private CardView       cardVersiculo;
    private TextView       tvPlaceholder;
    private TextView       tvTexto;
    private View           viewDivider;
    private TextView       tvReferencia;
    private Chip           chipCat;
    private ImageButton    btnFavorito;
    private ChipGroup      chipGroup;
    private MaterialButton btnSortear;

    private Animation animFlipOut, animFlipIn, animPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindViews();
        loadAnimations();
        setupViewModel();
    }

    // ── Bind ─────────────────────────────────────────────────────────────

    private void bindViews() {
        cardVersiculo = findViewById(R.id.card_versiculo);
        tvPlaceholder = findViewById(R.id.tv_placeholder);
        tvTexto       = findViewById(R.id.tv_texto_versiculo);
        viewDivider   = findViewById(R.id.view_divider);
        tvReferencia  = findViewById(R.id.tv_referencia);
        chipCat       = findViewById(R.id.chip_categoria_versiculo);
        btnFavorito   = findViewById(R.id.btn_favorito);
        chipGroup     = findViewById(R.id.chip_group_categorias);
        btnSortear    = findViewById(R.id.btn_sortear);

        btnSortear.setOnClickListener(v -> viewModel.sortear());
        btnFavorito.setOnClickListener(v -> viewModel.toggleFavorito());
    }

    private void loadAnimations() {
        animFlipOut = AnimationUtils.loadAnimation(this, R.anim.card_flip_out);
        animFlipIn  = AnimationUtils.loadAnimation(this, R.anim.card_flip_in);
        animPulse   = AnimationUtils.loadAnimation(this, R.anim.btn_pulse);
    }

    // ── ViewModel ─────────────────────────────────────────────────────────

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getVersiculoAtual().observe(this, this::exibirVersiculo);

        viewModel.getIsLoading().observe(this, loading -> {
            btnSortear.setEnabled(!loading);
            btnSortear.setAlpha(loading ? 0.55f : 1f);
        });

        viewModel.getCategorias().observe(this, this::montarChips);
    }

    // ── Menu ──────────────────────────────────────────────────────────────

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_historico) {
            startActivity(new Intent(this, HistoricoActivity.class));
            return true;
        } else if (id == R.id.action_favoritos) {
            startActivity(new Intent(this, FavoritosActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ── Chips ─────────────────────────────────────────────────────────────

    private void montarChips(List<String> categorias) {
        chipGroup.removeAllViews();

        // Cores definidas programaticamente — evita o crash do getColorStateList com theme
        int corSelecionado   = ContextCompat.getColor(this, R.color.gold_light);
        int corNaoSelecionado = ContextCompat.getColor(this, R.color.navy_mid);
        int textoSelecionado  = ContextCompat.getColor(this, R.color.navy_dark);
        int textoNaoSel       = ContextCompat.getColor(this, R.color.text_secondary);

        int[][] states = new int[][]{
            new int[]{ android.R.attr.state_checked},
            new int[]{-android.R.attr.state_checked}
        };

        ColorStateList bgColors   = new ColorStateList(states, new int[]{corSelecionado, corNaoSelecionado});
        ColorStateList textColors = new ColorStateList(states, new int[]{textoSelecionado, textoNaoSel});

        // Chip "Todas"
        chipGroup.addView(criarChip("Todas", true, bgColors, textColors, null));

        if (categorias == null) return;
        for (String cat : categorias) {
            chipGroup.addView(criarChip(cat, false, bgColors, textColors, cat));
        }
    }

    private Chip criarChip(String label, boolean checked,
                            ColorStateList bg, ColorStateList text,
                            String categoriaFiltro) {
        Chip chip = new Chip(this);
        chip.setText(label);
        chip.setCheckable(true);
        chip.setChecked(checked);
        chip.setChipBackgroundColor(bg);
        chip.setTextColor(text);
        chip.setChipStrokeWidth(0f);
        chip.setOnCheckedChangeListener((c, isChecked) -> {
            if (isChecked) viewModel.setCategoriaFiltro(categoriaFiltro);
        });
        return chip;
    }

    // ── Exibição ──────────────────────────────────────────────────────────

    private void exibirVersiculo(Versiculo v) {
        if (v == null) {
            Toast.makeText(this, getString(R.string.erro_sem_versiculo), Toast.LENGTH_SHORT).show();
            return;
        }

        animFlipOut.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation a) {}
            @Override public void onAnimationRepeat(Animation a) {}

            @Override
            public void onAnimationEnd(Animation a) {
                tvPlaceholder.setVisibility(View.GONE);

                tvTexto.setText(v.getTexto());
                tvTexto.setVisibility(View.VISIBLE);

                viewDivider.setVisibility(View.VISIBLE);

                tvReferencia.setText(v.getReferencia());
                tvReferencia.setVisibility(View.VISIBLE);

                chipCat.setText(v.getCategoria());
                chipCat.setVisibility(View.VISIBLE);

                btnFavorito.setImageResource(v.isFavorito()
                        ? R.drawable.ic_favorite_filled
                        : R.drawable.ic_favorite_border);
                btnFavorito.setVisibility(View.VISIBLE);

                cardVersiculo.startAnimation(animFlipIn);
            }
        });

        cardVersiculo.startAnimation(animFlipOut);
        btnSortear.startAnimation(animPulse);
    }
}
