package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryDetailFragment extends Fragment {

    private ImageView imagen;
    private TextView nombre;
    private TextView telefono;
    private TextView extension;
    private Contacto contacto;


    public DirectoryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directory_detail, container, false);
    }

    public void mostrarContacto (Contacto contacto) {
        this.contacto = contacto;
        imagen = (ImageView) getView().findViewById(R.id.imagen_contacto) ;
        imagen.setImageResource(R.drawable.contacto);
        nombre = (TextView) getView().findViewById(R.id.nombre_de_contacto);
        nombre.setText(contacto.getNombre());
        telefono = (TextView) getView().findViewById(R.id.telefono_de_contacto);
        telefono.setText(contacto.getNombre());
        extension = (TextView) getView().findViewById(R.id.extension_de_contacto);
        extension.setText(contacto.getNombre());
    }


}
