package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;

import java.util.ArrayList;

/**
 * Fragmento que será utilizado para mostrar
 * el buzón de sugerencias, el cual permite ver
 * las sugerencias que ha propuesto el usuario
 * y el estado en que se encuentran.
 */
public class MailboxFragment extends Fragment {

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

        GridLayout gridLayout = (GridLayout) vista.findViewById(R.id.grid_layout);

        ArrayList<String> sugerencias = new ArrayList<String>();
        sugerencias.add("Sugerencia 1");
        sugerencias.add("Sugerencia 2");
        sugerencias.add("Sugerencia 3");
        sugerencias.add("Sugerencia 4");
        sugerencias.add("Sugerencia 5");
        sugerencias.add("Sugerencia 6");
        sugerencias.add("Sugerencia 7");

        int veinteDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getContext().getResources().getDisplayMetrics());

        for (int i = 0; i < sugerencias.size(); i++) {

            TextView asunto = new TextView(getContext());
            asunto.setText(sugerencias.get(i));
            asunto.setTextSize(veinteDP);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(0));
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.rightMargin = veinteDP*4;
            param.leftMargin = veinteDP*4;
            asunto.setLayoutParams(param);
            gridLayout.addView(asunto);

            CheckBox checkBox1 = new CheckBox(getContext());
            checkBox1.setChecked(((int) (Math.random()*2)) == 1 ? true : false);
            GridLayout.LayoutParams param2 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(1));
            param2.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param2.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param2.rightMargin = veinteDP*4;
            checkBox1.setLayoutParams(param2);
            gridLayout.addView(checkBox1);

            CheckBox checkBox2 = new CheckBox(getContext());
            checkBox2.setChecked(((int) (Math.random()*2)) == 1 ? true : false);
            GridLayout.LayoutParams param3 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(2));
            param3.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param3.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param3.rightMargin = veinteDP*4;
            checkBox2.setLayoutParams(param3);
            gridLayout.addView(checkBox2);

            CheckBox checkBox3 = new CheckBox(getContext());
            checkBox3.setChecked(((int) (Math.random()*2)) == 1 ? true : false);
            GridLayout.LayoutParams param4 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(3));
            param4.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param4.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param4.rightMargin = veinteDP*4;
            checkBox3.setLayoutParams(param4);
            gridLayout.addView(checkBox3);

        }

        return vista;
    }

}
