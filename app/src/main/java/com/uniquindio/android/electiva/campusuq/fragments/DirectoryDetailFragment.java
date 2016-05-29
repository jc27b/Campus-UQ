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
 * Fragmento que se encargará de mostrar de una lista de
 * contactos de una dependencia cuando esta es seleccionada
 * en otro fragmento.
 */
public class DirectoryDetailFragment extends Fragment implements AdaptadorDeContacto.OnClickAdaptadorDeContacto {

    private RecyclerView listadoDeContactos;
    private ArrayList<Contacto> dependencia;
    private AdaptadorDeContacto adaptador;
    private OnContactoSeleccionadoListener listener;

    /**
     * Constructor del fragmento que mostrará
     * la lista de contactos.
     */
    public DirectoryDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Método llamado cuando se crea el fragmento.
     * Llama al mismo de la superclase.
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directory_detail, container, false);
    }

    /**
     * Método ejecutado cuando se crea la actividad.
     * Se encarga de inicializar el recycler view y
     * ponerle su respectivo adaptador.
     * @param savedInstanceState Instancia guardada para restaurar los datos.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoDeContactos = (RecyclerView) getView().findViewById(R.id.RecView3);

        adaptador = new AdaptadorDeContacto(dependencia, this);

        listadoDeContactos.setAdapter(adaptador);

        listadoDeContactos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    /**
     * Método para obtener el adaptador de la lista de contactos.
     * @return Adaptador de la lista de contactos.
     */
    public AdaptadorDeContacto getAdaptador() {
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
                listener = (OnContactoSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnDependenciaSeleccionadaListener");
            }
        }

    }

    /**
     * Método que será llamado cuando se presiona un item
     * de la lista de contactos, el cual notificará a la
     * actividad esta acción y ésta tomará las medidas
     * correspondientes.
     * @param pos Posición en la lista de contactos.
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onContactoSeleccionado(pos);
    }

    /**
     * Interfaz que sera implementada por la actividad para
     * compartir datos con otro fragmento.
     */
    public interface OnContactoSeleccionadoListener {
        void onContactoSeleccionado(int position);
    }

    /**
     * Método para asignar una lista de contactos.
     * @param dependencia Lista de contactos.
     */
    public void setDependencia(ArrayList<Contacto> dependencia) {
        this.dependencia = dependencia;
    }

    /**
     * Método para obtener la lista de contactos.
     * @return Lista de contactos.
     */
    public ArrayList<Contacto> getDependencia() {
        return dependencia;
    }



}
