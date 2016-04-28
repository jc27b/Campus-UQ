package com.uniquindio.android.electiva.campusuq.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeContacto;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryDetailFragment extends Fragment implements AdaptadorDeContacto.OnClickAdaptadorDeContacto {

    private RecyclerView listadoDeContactos;
    private ArrayList<Contacto> dependencia;
    private AdaptadorDeContacto adaptador;
    private OnContactoSeleccionadoListener listener;

    public DirectoryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directory_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeContactos = (RecyclerView) getView().findViewById(R.id.RecView3);

        adaptador = new AdaptadorDeContacto(dependencia, this);

        listadoDeContactos.setAdapter(adaptador);

        listadoDeContactos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    public AdaptadorDeContacto getAdaptador() {
        return adaptador;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                listener = (OnContactoSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnDependenciaSeleccionadaListener");
            }
        }

    }

    @Override
    public void onClickPosition(int pos) {
        listener.onContactoSeleccionado(pos);
    }

    public interface OnContactoSeleccionadoListener {
        void onContactoSeleccionado(int position);
    }

    public void setDependencia(ArrayList<Contacto> dependencia) {
        this.dependencia = dependencia;
    }

    public ArrayList<Contacto> getDependencia() {
        return dependencia;
    }



}
