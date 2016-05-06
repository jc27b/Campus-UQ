package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment {


    public LoginFragment() {
        // Required empty public constructor
    }


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
