package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryContainerFragment extends Fragment {

    public final Handler handler = new Handler();
    public Runnable runPager;

    public DirectoryContainerFragment() {
        // Required empty public constructor
    }

    public static DirectoryContainerFragment newContainerInstance() {

        DirectoryContainerFragment fragment = new DirectoryContainerFragment();

        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_directory_container, container, false);

        runPager = new Runnable() {

            @Override
            public void run()
            {
                DirectoryFragment directoryFragment = new DirectoryFragment();

                ArrayList<Dependencia> directorio = new ArrayList<Dependencia>();

                for (int i = 1; i < 4; i++) {
                    Dependencia dependencia = new Dependencia();
                    dependencia.setNombre("Dependencia "+i);
                    ArrayList<Contacto> contactos = new ArrayList<Contacto>();
                    for (int j = 1; j < 4; j++) {
                        Contacto contacto = new Contacto();
                        contacto.setNombre("Contacto "+j+" de la Dependencia "+i);
                        contacto.setTelefono("735930"+(i*j));
                        contacto.setExtension("364-925-7"+i+""+j);
                        contactos.add(contacto);
                    }
                    dependencia.setContactos(contactos);
                    directorio.add(dependencia);
                }

                directoryFragment.setDirectorio(directorio);

                DirectoryDetailFragment directoryDetailFragment = new DirectoryDetailFragment();

                directoryDetailFragment.setDependencia(directorio.get(0).getContactos());

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.directory_container_left, directoryFragment, "directoryFragment");
                if (vista.findViewById(R.id.directory_container_right) != null) {
                    fragmentTransaction.replace(R.id.directory_container_right, directoryDetailFragment, "directoryDetailFragment");
                }
                fragmentTransaction.commitAllowingStateLoss();
                getActivity().getSupportFragmentManager().executePendingTransactions();

            }
        };
        handler.post(runPager);

        return vista;
    }


    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runPager);
    }

}
