package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeDetailFragment extends Fragment {

    private ImageView imagen;
    private TextView titulo;
    private TextView id;
    private TextView fecha;
    private TextView detalle;

    private Noticia noticia;
    private android.support.design.widget.FloatingActionButton btnCompartirFacebook;
    private android.support.design.widget.FloatingActionButton btnCompartirTwitter;


    public NoticeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice_detail, container, false);
    }

    public void mostrarNoticia (Noticia noticia) {
        this.noticia = noticia;
        imagen = (ImageView) getView().findViewById(R.id.imagen_detalle);
        imagen.setImageBitmap(noticia.getImagen());
        titulo = (TextView) getView().findViewById(R.id.titulo_de_detalle_noticia);
        titulo.setText(noticia.getTitulo());
        id = (TextView) getView().findViewById(R.id.id_de_detalle_noticia);
        id.setText(noticia.getId());
        fecha = (TextView) getView().findViewById(R.id.fecha_de_detalle_noticia);
        fecha.setText(noticia.getFecha());
        detalle = (TextView) getView().findViewById(R.id.descripcion_de_detalle_noticia);
        detalle.setText(noticia.getDescripcion());
        btnCompartirFacebook = (android.support.design.widget.FloatingActionButton) getView().findViewById(R.id.btn_compartir_facebook);
        btnCompartirFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Se ha compartido la noticia en Facebook", Toast.LENGTH_SHORT).show();
            }
        });
        btnCompartirTwitter = (android.support.design.widget.FloatingActionButton) getView().findViewById(R.id.btn_compartir_twitter);
        btnCompartirTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Se ha compartido la noticia en Twitter", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
