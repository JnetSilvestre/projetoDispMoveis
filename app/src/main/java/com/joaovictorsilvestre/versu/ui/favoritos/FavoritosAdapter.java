package com.joaovictorsilvestre.versu.ui.favoritos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joaovictorsilvestre.versu.R;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;

import java.util.ArrayList;
import java.util.List;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolder> {

    public interface OnRemoveListener { void onRemove(Versiculo v); }

    private List<Versiculo> lista = new ArrayList<>();
    private final OnRemoveListener listener;

    public FavoritosAdapter(OnRemoveListener listener) { this.listener = listener; }

    public void setLista(List<Versiculo> nova) {
        this.lista = nova != null ? nova : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorito, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        Versiculo v = lista.get(pos);
        h.tvTexto.setText(v.getTexto());
        h.tvReferencia.setText(v.getReferencia());
        h.tvCategoria.setText(v.getCategoria());
        h.btnRemover.setOnClickListener(x -> listener.onRemove(v));
    }

    @Override
    public int getItemCount() { return lista.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTexto, tvReferencia, tvCategoria;
        View     btnRemover;

        ViewHolder(View v) {
            super(v);
            tvTexto     = v.findViewById(R.id.tv_fav_texto);
            tvReferencia = v.findViewById(R.id.tv_fav_referencia);
            tvCategoria = v.findViewById(R.id.tv_fav_categoria);
            btnRemover  = v.findViewById(R.id.btn_fav_remover);
        }
    }
}
