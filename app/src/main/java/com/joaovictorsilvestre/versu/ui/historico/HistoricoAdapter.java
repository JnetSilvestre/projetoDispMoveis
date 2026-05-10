package com.joaovictorsilvestre.versu.ui.historico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joaovictorsilvestre.versu.R;
import com.joaovictorsilvestre.versu.data.model.HistoricoComVersiculo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    public interface OnDeleteListener {
        void onDelete(HistoricoComVersiculo item);
    }

    private List<HistoricoComVersiculo> lista = new ArrayList<>();
    private final OnDeleteListener listener;
    private final SimpleDateFormat sdf =
            new SimpleDateFormat("dd/MM/yyyy  HH:mm", Locale.getDefault());

    public HistoricoAdapter(OnDeleteListener listener) {
        this.listener = listener;
    }

    public void setLista(List<HistoricoComVersiculo> novaLista) {
        this.lista = novaLista != null ? novaLista : new ArrayList<>();
        notifyDataSetChanged();
    }

    /** Retorna item para uso pelo ItemTouchHelper (swipe). */
    public HistoricoComVersiculo getItem(int position) {
        return lista.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historico, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        HistoricoComVersiculo hcv = lista.get(pos);
        if (hcv.versiculo != null) {
            h.tvTexto.setText(hcv.versiculo.getTexto());
            h.tvReferencia.setText(hcv.versiculo.getReferencia());
            h.tvCategoria.setText(hcv.versiculo.getCategoria());
        }
        String data = sdf.format(new Date(hcv.historico.getDataSorteio()));
        h.tvData.setText(data);

        h.btnDelete.setOnClickListener(v -> listener.onDelete(hcv));
    }

    @Override
    public int getItemCount() { return lista.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTexto, tvReferencia, tvCategoria, tvData;
        View     btnDelete;

        ViewHolder(View v) {
            super(v);
            tvTexto     = v.findViewById(R.id.tv_hist_texto);
            tvReferencia = v.findViewById(R.id.tv_hist_referencia);
            tvCategoria = v.findViewById(R.id.tv_hist_categoria);
            tvData      = v.findViewById(R.id.tv_hist_data);
            btnDelete   = v.findViewById(R.id.btn_hist_delete);
        }
    }
}
