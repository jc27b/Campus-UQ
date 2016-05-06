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
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeNoticia;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

/**
 * Fragmento que se encargará de mostrar un listado de noticias.
 * Declara una interfaz para asegurar que la actividad tenga como compartir
 * la informacion con otro fragmento.
 */
public class NoticeFragment extends Fragment implements AdaptadorDeNoticia.OnClickAdaptadorDeNoticia {

    private RecyclerView listadoDeNoticias;
    private ArrayList<Noticia> noticias;
    private AdaptadorDeNoticia adaptador;
    private OnNoticiaSeleccionadaListener listener;

    /**
     * Constructor del fragmento que mostrará
     * la lista de noticias.
     */
    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo llamado cuando se crea el fragmento,
     * llama al método de la superclase
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Metodo que se encarga de crear la vista que utilizara
     * el fragmento, por medio del metodo inflate.
     * @param inflater Encargado de poner la vista en el fragmento.
     * @param container Jerarquia de vistas de la actividad.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     * @return Vista creada para el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        return view;

    }

    /**
     * Metodo llamado cuando la actividad se termina de crear,
     * inicializa la vista RecyclerView, un adaptador para la lista,
     * setea el adaptador y finalmente configura la forma en que se
     * mostrara la lista con el LayoutManager.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeNoticias = (RecyclerView) getView().findViewById(R.id.RecView);

        adaptador = new AdaptadorDeNoticia(noticias, this);

        listadoDeNoticias.setAdapter(adaptador);

        listadoDeNoticias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


    }

    /**
     * Método para obtener el adaptador de la lista de noticias.
     * @return Adaptador de la lista de noticias.
     */
    public AdaptadorDeNoticia getAdaptador() {
        return adaptador;
    }

    /**
     * Metodo llamado cuando se va a agregar el fragmento dentro
     * de la actividad, es utilizado en este caso para confirmar que
     * la actividad implemente una interfaz para pasar datos
     * a otro fragmento.
     * @param context Actividad del fragmento.
     */
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

    /**
     * Método que será llamado cuando se presiona un item
     * de la lista de noticias, el cual notificará a la
     * actividad esta acción y ésta tomará las medidas
     * correspondientes.
     * @param pos Posición en la lista de noticias.
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onNoticiaSeleccionada(pos);
    }

    /**
     * Interfaz que sera implementada por la actividad para
     * compartir datos con otro fragmento.
     */
    public interface OnNoticiaSeleccionadaListener {
        void onNoticiaSeleccionada(int position);
    }

    /**
     * Método para asignar una lista de noticias.
     * @param noticias Lista de noticias.
     */
    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    /**
     * Método para obtener una lista de noticias.
     * @return Lista de noticias.
     */
    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }


}
