package com.uniquindio.android.electiva.campusuq.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.CRUD;
import com.uniquindio.android.electiva.campusuq.util.CRUDSQL;
import com.uniquindio.android.electiva.campusuq.util.Utilidades;
import com.uniquindio.android.electiva.campusuq.vo.Sugerencia;

import java.util.ArrayList;

/**
 * Fragmento que será utilizado para mostrar
 * el buzón de sugerencias, el cual permite ver
 * las sugerencias que ha propuesto el usuario
 * y el estado en que se encuentran.
 */
public class MailboxFragment extends Fragment {

    private ArrayList<Sugerencia> sugerencias;

    private CRUDSQL crudsql;

    /**
     * Constructor por defecto del fragmento
     * el cual es necesario que sea publico
     * y vacio para instanciarlo
     */
    public MailboxFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que permite crear una instacia del fragmento.
     * @return Instancia del fragmento que será utilizada en el view pager según el adaptador.
     */
    public static MailboxFragment newInstance() {

        MailboxFragment fragment = new MailboxFragment();

        return fragment;

    }

    /**
     * Metodo llamado cuando se crea el fragmento,
     * llama a otro callback de la superclase.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Método que crea la vista que utilizará el fragmento,
     * ésta es alterada programáticamente para agregar
     * una lista de sugerencias al grid layout con sus
     * respectivos checkbox que indican el estado.
     * @param inflater Objeto que permitirá inflar la vista para el fragmento
     * @param container Contenedor según la jerarquía de vistas de la actividad
     * @param savedInstanceState Instancia guardada para restaurar los datos
     * @return Vista que usará el fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mailbox, container, false);

        return vista;
    }

    /**
     * Método ejecutado cuando se crea la actividad.
     * Se encarga se inicializar el listado de sugerencias.
     * @param savedInstanceState Instanncia guardada para restaurar los datos.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        crudsql = new CRUDSQL(getActivity(), 1);
        setSugerencias(crudsql.getSugerencias());

        HiloSecundarioSugerencia hiloSecundario = new HiloSecundarioSugerencia(this.getContext());
        hiloSecundario.execute(Utilidades.LISTAR_SUGERENCIAS);
    }

    /**
     * Método que permite obtener las sugerencias.
     * @return Sugerencias del fragmento.
     */
    public ArrayList<Sugerencia> getSugerencias() {
        return sugerencias;
    }

    /**
     * Método que permite establecer las sugerencias.
     * @param sugerencias Sugerencias a establecer.
     */
    public void setSugerencias(ArrayList<Sugerencia> sugerencias) {
        this.sugerencias = sugerencias;
    }

    /**
     * Clase que implementa un hilo secundario para realizar
     * operaciones con con servicios.
     */
    public class HiloSecundarioSugerencia extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progress;
        private Context context;
        private Sugerencia sugerencia;

        /**
         * Constructor del hilo secundario, que
         * inicializa el contexto y la sugerencia.
         * @param context Contexto de la aplicación.
         */
        public HiloSecundarioSugerencia(Context context) {
            this.context = context;
            sugerencia = null;
        }

        /**
         * Metodo ejecutado antes de que
         * se ejecute el hilo, muestra un
         * mensaje informativo.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(context, context.getString(R.string.cargando_sugerencias), context.getString(R.string.espere), true);
        }

        /**
         * Metodo que se ejecuta en el hilo secundario,
         * el cual permite listar las sugerencias.
         * @param params Operación a realizar.
         * @return Operación realizada.
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            if (params[0] == Utilidades.LISTAR_SUGERENCIAS) {
                setSugerencias(CRUD.getListaDeSugerencias());
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

            if (integer == Utilidades.LISTAR_SUGERENCIAS) {

                GridLayout gridLayout = (GridLayout) getView().findViewById(R.id.grid_layout);

                int veinteDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getContext().getResources().getDisplayMetrics());

                for (int i = 0; i < sugerencias.size(); i++) {

                    TextView asunto = new TextView(getContext());
                    asunto.setText(sugerencias.get(i).getAsunto());
                    asunto.setTextSize(veinteDP);
                    GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(0));
                    param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    param.rightMargin = veinteDP*4;
                    param.leftMargin = veinteDP*4;
                    asunto.setLayoutParams(param);
                    gridLayout.addView(asunto);

                    CheckBox checkBox1 = new CheckBox(getContext());
                    checkBox1.setChecked(sugerencias.get(i).getEstado().equals("Revisando"));
                    GridLayout.LayoutParams param2 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(1));
                    param2.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    param2.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    param2.rightMargin = veinteDP*4;
                    checkBox1.setLayoutParams(param2);
                    gridLayout.addView(checkBox1);

                    CheckBox checkBox2 = new CheckBox(getContext());
                    checkBox2.setChecked(sugerencias.get(i).getEstado().equals("Declinado"));
                    GridLayout.LayoutParams param3 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(2));
                    param3.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    param3.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    param3.rightMargin = veinteDP*4;
                    checkBox2.setLayoutParams(param3);
                    gridLayout.addView(checkBox2);

                    CheckBox checkBox3 = new CheckBox(getContext());
                    checkBox3.setChecked(sugerencias.get(i).getEstado().equals("Corregido"));
                    GridLayout.LayoutParams param4 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(3));
                    param4.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    param4.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    param4.rightMargin = veinteDP*4;
                    checkBox3.setLayoutParams(param4);
                    gridLayout.addView(checkBox3);

                }

            }

            progress.dismiss();
        }

        /**
         * Permite obtener la sugerencia del hilo.
         * @return Sugerencia del hilo.
         */
        public Sugerencia getSugerencia() {
            return sugerencia;
        }

        /**
         * Permite establecer la sugerencia del hilo.
         * @param sugerencia Sugerencia a establecer.
         */
        public void setSugerencia(Sugerencia sugerencia) {
            this.sugerencia = sugerencia;
        }

    }

}
