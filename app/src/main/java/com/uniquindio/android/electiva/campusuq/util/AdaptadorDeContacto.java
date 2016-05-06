package com.uniquindio.android.electiva.campusuq.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryDetailFragment;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;

import java.util.ArrayList;

public class AdaptadorDeContacto extends RecyclerView.Adapter<AdaptadorDeContacto.ContactoViewHolder> {

    private ArrayList<Contacto> dependencia;
    private static OnClickAdaptadorDeContacto listener;

    public AdaptadorDeContacto(ArrayList<Contacto> dependencia, DirectoryDetailFragment directoryDetailFragment) {
        this.dependencia = dependencia;
        listener = (OnClickAdaptadorDeContacto) directoryDetailFragment;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_contacto, parent, false);
        ContactoViewHolder noticiaVH = new ContactoViewHolder(itemView);
        return noticiaVH;
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        Contacto contacto = dependencia.get(position);
        holder.binContacto(contacto);
    }

    @Override
    public int getItemCount() {
        return dependencia.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNombre;
        private TextView txtTelefono;
        private TextView txtExtension;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre_de_contacto);
            txtTelefono = (TextView) itemView.findViewById(R.id.telefono_de_contacto);
            txtExtension = (TextView) itemView.findViewById(R.id.extension_de_contacto);
        }

        public void binContacto(Contacto c) {
            txtNombre.setText(c.getNombre());
            txtTelefono.setText(c.getTelefono());
            txtExtension.setText(c.getExtension());
        }

        @Override
        public void onClick(View v) {
            listener.onClickPosition(getAdapterPosition());
        }
    }

    public interface OnClickAdaptadorDeContacto {
        public void onClickPosition(int pos);
    }



}
