package com.uniquindio.android.electiva.campusuq.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

/**
 * Fragmento de la página de directorio en el viewpager,
 * que contendrá fragmentos de directorio y su detalle.
 */
public class DirectoryContainerFragment extends Fragment {

    public final Handler handler = new Handler();
    public Runnable runPager;

    /**
     * Constructor vacío para instanciar el fragmento.
     */
    public DirectoryContainerFragment() {
        // Required empty public constructor
    }

    /**
     * Creador de una nueva instancia del fragmento.
     * @return Nueva instancia del fragmento.
     */
    public static DirectoryContainerFragment newContainerInstance() {

        DirectoryContainerFragment fragment = new DirectoryContainerFragment();

        return fragment;

    }

    /**
     * Método llamado cuando se crea el fragmento.
     * Llama a otro callback de la superclase.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * Método que configura la vista que utilizará el fragmento. Asigna un
     * listener al botón de actualizar y configura una lista de datos
     * (dependencias y contactos que será mostrada por los fragmentos
     * que contiene, éstos son añadidos de acuerdo a la configuración
     * actual del dispositivo.
     * @param inflater Objeto para inflar la vista del fragmento.
     * @param container Grupo de vistas padre en el que se inserta la vista.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     * @return Vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_directory_container, container, false);

        ImageButton imageButton = (ImageButton) vista.findViewById(R.id.btn_update);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getResources().getString(R.string.directorio_actualizado), Toast.LENGTH_SHORT).show();
            }
        });

        runPager = new Runnable() {

            @Override
            public void run()
            {
                DirectoryFragment directoryFragment = new DirectoryFragment();

                ArrayList<Dependencia> directorio = new ArrayList<Dependencia>();

                for (int i = 1; i <= 5; i++) {
                    Bitmap imagen = null;
                    String nombre = "Dependencia "+i;
                    switch (i) {
                        case 1:
                            imagen = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.rectoria);
                            nombre = "Rectoría";
                            break;
                        case 2:
                            imagen = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.vacad);
                            nombre = "Vicerrectoría Académica";
                            break;
                        case 3:
                            imagen = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.vadmin);
                            nombre = "Vicerrectoría Administrativa";
                            break;
                        case 4:
                            imagen = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.vextydessocial);
                            nombre = "Vicerrectoría de Extensión y Desarrollo Social";
                            break;
                        case 5:
                            imagen = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.vinvestigaciones);
                            nombre = "Vicerrectoría de Investigaciones";
                            break;
                    }

                    ArrayList<Contacto> contactos = new ArrayList<Contacto>();
                    for (int j = 1; j <= 7; j++) {
                        Contacto contacto = new Contacto("Contacto "+j+" de la Dependencia "+i,"735930"+(i*j),"364-925-7"+i+""+j);
                        contactos.add(contacto);
                    }
                    Dependencia dependencia = new Dependencia(imagen,nombre,contactos);
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

    /**
     * Método llamado cuando se pausa el fragmento.
     * Se encarga de quitar los callbacks que
     * tiene el hilo.
     */
    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runPager);
    }

}
