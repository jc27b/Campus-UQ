package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeDetailFragment extends Fragment {

    private TextView titulo;
    private Noticia noticia;
    private Button btnCompartirFacebook;
    private Button btnCompartirTwitter;


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
        titulo = (TextView) getView().findViewById(R.id.titulo_de_detalle_noticia);
        titulo.setText(noticia.getTitulo());
        btnCompartirFacebook = (Button) getView().findViewById(R.id.btn_compartir_facebook);
        btnCompartirFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Se ha compartido la noticia en Facebook", Toast.LENGTH_SHORT).show();
            }
        });
        btnCompartirTwitter = (Button) getView().findViewById(R.id.btn_compartir_twitter);
        btnCompartirTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Se ha compartido la noticia en Twitter", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
