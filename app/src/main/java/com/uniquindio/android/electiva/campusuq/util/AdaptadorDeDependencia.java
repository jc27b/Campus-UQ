package com.uniquindio.android.electiva.campusuq.util;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryFragment;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

public class AdaptadorDeDependencia extends RecyclerView.Adapter<AdaptadorDeDependencia.DependenciaViewHolder> {

    private ArrayList<Dependencia> directorio;
    private static OnClickAdaptadorDeDirectorio listener;

    public AdaptadorDeDependencia(ArrayList<Dependencia> directorio, DirectoryFragment directoryFragment) {
        this.directorio = directorio;
        listener = (OnClickAdaptadorDeDirectorio) directoryFragment;
    }

    @Override
    public DependenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_dependencia, parent, false);
        DependenciaViewHolder noticiaVH = new DependenciaViewHolder(itemView);
        return noticiaVH;
    }

    @Override
    public void onBindViewHolder(DependenciaViewHolder holder, int position) {
        Dependencia dependencia = directorio.get(position);
        holder.binDependencia(dependencia);
    }

    @Override
    public int getItemCount() {
        return directorio.size();
    }

    public static class DependenciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNombre;
        private ImageView imagen;

        public DependenciaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
        }

        public void binDependencia(Dependencia d) {
            txtNombre.setText(d.getNombre());
            imagen.setImageResource(R.drawable.vinvestigaciones);
        }

        @Override
        public void onClick(View v) {
            listener.onClickPosition(getAdapterPosition());
        }
    }

    public interface OnClickAdaptadorDeDirectorio {
        public void onClickPosition(int pos);
    }



}
