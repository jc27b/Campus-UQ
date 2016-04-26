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
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeDirectorio;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryFragment extends Fragment implements AdaptadorDeDirectorio.OnClickAdaptadorDeDirectorio {

    private RecyclerView listadoDeDependencias;
    private ArrayList<Dependencia> directorio;
    private AdaptadorDeDirectorio adaptador;
    private OnDependenciaSeleccionadaListener listener;

    public DirectoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeDependencias = (RecyclerView) getView().findViewById(R.id.RecView2);

        adaptador = new AdaptadorDeDirectorio(directorio, this);

        listadoDeDependencias.setAdapter(adaptador);

        listadoDeDependencias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


    }

    public AdaptadorDeDirectorio getAdaptador() {
        return adaptador;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                listener = (OnDependenciaSeleccionadaListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnDependenciaSeleccionadaListener");
            }
        }

    }

    @Override
    public void onClickPosition(int pos) {
        listener.onDependenciaSeleccionada(pos);
    }

    public interface OnDependenciaSeleccionadaListener {
        void onDependenciaSeleccionada(int position);
    }

    public void setDirectorio(ArrayList<Dependencia> directorio) {
        this.directorio = directorio;
    }

    public ArrayList<Dependencia> getDirectorio() {
        return directorio;
    }


}
