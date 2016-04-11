package com.uniquindio.android.electiva.campusuq.util;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    private RecyclerView listadoDeNoticias;
    private ArrayList<Noticia> noticias;
    private AdaptadorDeNoticia adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        noticias = new ArrayList<Noticia>();

        noticias.add(new Noticia("Noticia 1"));
        noticias.add(new Noticia("Noticia 2"));
        noticias.add(new Noticia("Noticia 3"));

        listadoDeNoticias = (RecyclerView) view.findViewById(R.id.RecView);

        adaptador = new AdaptadorDeNoticia(noticias);

        listadoDeNoticias.setAdapter(adaptador);

        listadoDeNoticias.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;

    }

    public AdaptadorDeNoticia getAdaptador() {
        return adaptador;
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

}
