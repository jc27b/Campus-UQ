package com.uniquindio.android.electiva.campusuq.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.activity.MainActivity;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeDependencia;
import com.uniquindio.android.electiva.campusuq.util.CRUD;
import com.uniquindio.android.electiva.campusuq.util.CRUDSQL;
import com.uniquindio.android.electiva.campusuq.util.Utilidades;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

/**
 * Fragmento que se encargará de mostrar un listado de dependencias.
 * Declara una interfaz para asegurar que la actividad tenga como compartir
 * la informacion con otro fragmento.
 */
public class DirectoryFragment extends Fragment implements AdaptadorDeDependencia.OnClickAdaptadorDeDirectorio {

    private RecyclerView listadoDeDependencias;
    private ArrayList<Dependencia> directorio;
    private AdaptadorDeDependencia adaptador;
    private OnDependenciaSeleccionadaListener listener;

    public DirectoryFragment directoryFragment;
    private CRUDSQL crudsql;

    /**
     * Constructor del fragmento que mostrará
     * la lista de dependencias.
     */
    public DirectoryFragment() {
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

        directoryFragment = this;

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

        View view = inflater.inflate(R.layout.fragment_directory, container, false);

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

        listadoDeDependencias = (RecyclerView) getView().findViewById(R.id.RecView2);

        crudsql = new CRUDSQL(getActivity(), 1);
        setDirectorio(crudsql.getDependencias());

        if (directorio.size() == 0 && MainActivity.haveNetworkConnection(getContext())) {
            HiloSecundarioDependencia hiloSecundario = new HiloSecundarioDependencia(this.getContext());
            hiloSecundario.execute(Utilidades.LISTAR_DEPENDENCIAS);
        } else {
            adaptador = new AdaptadorDeDependencia(directorio, this);
            listadoDeDependencias.setAdapter(adaptador);
            listadoDeDependencias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }

    }

    /**
     * Método para obtener el adaptador de la lista de dependencias.
     * @return Adaptador de la lista de dependencias.
     */
    public AdaptadorDeDependencia getAdaptador() {
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
                listener = (OnDependenciaSeleccionadaListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnDependenciaSeleccionadaListener");
            }
        }

    }

    /**
     * Método que será llamado cuando se presiona un item
     * de la lista de dependencias, el cual notificará a la
     * actividad esta acción y ésta tomará las medidas
     * correspondientes.
     * @param pos Posición en la lista de dependencias.
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onDependenciaSeleccionada(pos);
    }

    /**
     * Interfaz que sera implementada por la actividad para
     * compartir datos con otro fragmento.
     */
    public interface OnDependenciaSeleccionadaListener {
        void onDependenciaSeleccionada(int position);
    }

    /**
     * Método para asignar una lista de dependencias.
     * @param directorio Lista de dependencias.
     */
    public void setDirectorio(ArrayList<Dependencia> directorio) {
        this.directorio = directorio;
    }

    /**
     * Método para obtener una lista de dependencias-
     * @return Lista de dependencias.
     */
    public ArrayList<Dependencia> getDirectorio() {
        return directorio;
    }

    public void actualizarDirectorio() {

        ArrayList<Contacto> contactos = crudsql.getContactos();
        for (Contacto contacto: contactos) {
            crudsql.elimarContacto(contacto.get_id());
        }

        for (Dependencia dependencia: directorio) {
            crudsql.elimarDependencia(dependencia.get_id());
        }

        HiloSecundarioDependencia hiloSecundario = new HiloSecundarioDependencia(this.getContext());
        hiloSecundario.execute(Utilidades.LISTAR_DEPENDENCIAS);

    }

    /**
     * Clase que implementa un hilo secundario para realizar
     * operaciones con con servicios.
     */
    public class HiloSecundarioDependencia extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progress;
        private Context context;
        private Dependencia dependencia;

        /**
         * Constructor del hilo secundario, que
         * inicializa el contexto y la dependencia.
         * @param context Contexto de la aplicación.
         */
        public HiloSecundarioDependencia(Context context) {
            this.context = context;
            dependencia = null;
        }

        /**
         * Metodo ejecutado antes de que
         * se ejecute el hilo, muestra un
         * mensaje informativo.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(context, context.getString(R.string.cargando_directorio), context.getString(R.string.espere), true);
        }

        /**
         * Metodo que se ejecuta en el hilo secundario,
         * el cual permite listar las dependencias.
         * @param params Operación a realizar.
         * @return Operación realizada.
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            if (params[0] == Utilidades.LISTAR_DEPENDENCIAS) {
                setDirectorio(CRUD.getListaDeDependencias());
            }

            return params[0];
        }

        /**
         * Metodo ejecutado después de que
         * el hilo finalizó su ejecución.
         * Pone el los controles gráficos
         * la información extraída del servicio.
         * @param integer Operación a realizar.
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (integer == Utilidades.LISTAR_DEPENDENCIAS) {
                if (adaptador == null) {
                    adaptador = new AdaptadorDeDependencia(directorio, directoryFragment);
                    listadoDeDependencias.setAdapter(adaptador);
                    listadoDeDependencias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                } else {
                    adaptador.intercambiar(directorio);
                }

                for (Dependencia dependencia: directorio) {
                    for (Contacto contacto: dependencia.getContactos()) {
                        crudsql.insertarContacto(dependencia.getNombre(), contacto.getNombre(), contacto.getTelefono(), contacto.getExtension());
                    }
                    crudsql.insertarDependencia(dependencia.getImagen(), dependencia.getNombre());
                }

            }

            progress.dismiss();
        }

        /**
         * Permite obtener la dependencia del hilo.
         * @return Dependencia del hilo.
         */
        public Dependencia getDependencia() {
            return dependencia;
        }

        /**
         * Permite establecer la dependencia del hilo.
         * @param dependencia Dependencia a establecer.
         */
        public void setDependencia(Dependencia dependencia) {
            this.dependencia = dependencia;
        }

    }


}
