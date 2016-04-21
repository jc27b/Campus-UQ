package com.uniquindio.android.electiva.campusuq.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeNoticia;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment implements AdaptadorDeNoticia.OnClickAdaptadorDeNoticia {

    private RecyclerView listadoDeNoticias;
    private ArrayList<Noticia> noticias;
    private AdaptadorDeNoticia adaptador;
    private OnNoticiaSeleccionadaListener listener;

    public NoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeNoticias = (RecyclerView) getView().findViewById(R.id.RecView);

        adaptador = new AdaptadorDeNoticia(noticias, this);

        listadoDeNoticias.setAdapter(adaptador);

        listadoDeNoticias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


    }

    public AdaptadorDeNoticia getAdaptador() {
        return adaptador;
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_agregar) {
            noticias.add(0, new Noticia("Nueva noticia"));
            adaptador.notifyItemInserted(0);
        }
        if (id == R.id.menu_eliminar) {
            noticias.remove(0);
            adaptador.notifyItemRemoved(0);
        }
        if (id == R.id.menu_modificar) {
            Noticia aux = noticias.get(1);
            noticias.set(1, noticias.get(2));
            noticias.set(2, aux);

            adaptador.notifyItemMoved(1, 2);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                listener = (OnNoticiaSeleccionadaListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnNoticiaSeleccionadaListener");
            }
        }

    }

    @Override
    public void onClickPosition(int pos) {
        listener.onNoticiaSeleccionada(pos);
    }

    public interface OnNoticiaSeleccionadaListener {
        void onNoticiaSeleccionada(int position);
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }
}
