package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;

/**
 * Fragmento de tipo dialogo que permite iniciar sesión,
 * para esto tiene los textos de las redes sociales, al
 * presionarlos se inicia sesión en ellas.
 */
public class LoginFragment extends DialogFragment {

    /**
     * Constructor del fragmento vacio para instanciarlo
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo donde se configura la vista que utilizará el fragmento,
     * además se le pone titulo al diálogo y un listener a los
     * textos para iniciar sesión en las redes sociales.
     * @param inflater Objeto que permite cargar una vista al fragmento
     * @param savedInstanceState  Instancia guardada para restaurar los datos
     * @return Vista que usará el fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_login, container, false);

        getDialog().setTitle(getResources().getString(R.string.iniciar_sesion));

        TextView txt1 = (TextView) vista.findViewById(R.id.txt_login_facebook);
        TextView txt2 = (TextView) vista.findViewById(R.id.txt_login_twitter);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getResources().getString(R.string.compartir_facebook), Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getResources().getString(R.string.compartir_twitter), Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        return vista;
    }

}
