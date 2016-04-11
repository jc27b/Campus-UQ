package com.uniquindio.android.electiva.campusuq.util;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

public class AdaptadorDeNoticia extends RecyclerView.Adapter<AdaptadorDeNoticia.NoticiaViewHolder> {

    private ArrayList<Noticia> noticias;

    public AdaptadorDeNoticia(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_noticia, parent, false);
        NoticiaViewHolder noticiaVH = new NoticiaViewHolder(itemView);
        return noticiaVH;
    }

    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.binNoticia(noticia);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitulo;

        public NoticiaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtTitulo = (TextView) itemView.findViewById(R.id.titulo);
        }

        public void binNoticia(Noticia n) {
            txtTitulo.setText(n.getTitulo());
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked." + txtTitulo.getText());
        }
    }

}
