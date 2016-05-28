package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

/**
 * Fragmento de la página de noticias en el viewpager,
 * que contendrá fragmentos de noticia y su detalle.
 */
public class NoticeContainerFragment extends Fragment {

    public final Handler handler = new Handler();
    public Runnable runPager;

    /**
     * Constructor vacío para instanciar el fragmento.
     */
    public NoticeContainerFragment() {
        // Required empty public constructor
    }

    /**
     * Creador de una nueva instancia del fragmento.
     * @return Nueva instancia del fragmento.
     */
    public static NoticeContainerFragment newContainerInstance() {

        NoticeContainerFragment fragment = new NoticeContainerFragment();

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
     * Método que configura la vista que utilizará el fragmento. Configura
     * una lista de noticias que será mostrada por los fragmentos
     * que contiene, éstos son añadidos de acuerdo a la configuración
     * actual del dispositivo.
     * @param inflater Objeto para inflar la vista del fragmento.
     * @param container Grupo de vistas padre en el que se inserta la vista.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     * @return Vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_notice_container, container, false);

        runPager = new Runnable() {

            @Override
            public void run()
            {
                NoticeFragment noticeFragment = new NoticeFragment();

                ArrayList<Noticia> noticias = new ArrayList<Noticia>();

                /*
                for (int i = 1; i <= 7; i++) {
                    String id = "ID: "+i;

                    Bitmap imagen = BitmapFactory.decodeResource(getContext().getResources(), ((int) (Math.random()*2)) == 1 ? R.drawable.noticias : R.drawable.detalle_noticia);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imagen.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    String imageString = Base64.encodeToString(byteArray, Base64.NO_WRAP);

                    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
                    String titulo = "Noticia "+i;
                    String date = df.format(Calendar.getInstance().getTime());
                    String detalle = "Detalle de la noticia "+i;
                    switch (i) {
                        case 1:
                            titulo = "Egresados aportan a la Alta Calidad en Ingeniería Electrónica";
                            detalle = "Con la visita de destacados ingenieros graduados del programa de Ingeniería electrónica de la Universidad del Quindío, en el marco de los procesos de Autoevaluación y el Proyecto Cultural promovidos desde el programa mencionado y la facultad de ingeniería, se realizó un ciclo conferencial y un minitaller  con los ingenieros Jaime Alberto Segura, PhD, investigador del Instituto Laue-Langevin (Institut Laue-Langevin o ILL), radicado en Grenoble Francia y Catalina Mejía, Msc, de la Universidad de Aschen, Alemania.\n\nLa uniquindiana Catalina Mejía recibió el título con honores en Ingeniería Electrónica de la Universidad del Quindío en el año 2011. Su trabajo recibió mención laureada por la calidad de su investigación en el área de optimización para comunicaciones móviles de 4ta generación en la Universidad alemana RWTH Aachen durante el año 2009. En el año 2015 obtuvo magister en Ingeniería Electrónica, Tecnología de la Información e Ingeniería Computacional de la RWTH Aachen, con profundización en la ingeniería de comunicaciones.";
                            break;
                    }
                    Noticia noticia = new Noticia(id,imageString,titulo,date,detalle);
                    noticias.add(noticia);
                }
                */

                noticeFragment.setNoticias(noticias);

                NoticeDetailFragment noticeDetailFragment = new NoticeDetailFragment();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.notice_container_left, noticeFragment, "noticeFragment");
                if (vista.findViewById(R.id.notice_container_right) != null) {
                    fragmentTransaction.replace(R.id.notice_container_right, noticeDetailFragment, "noticeDetailFragment");
                }
                fragmentTransaction.commitAllowingStateLoss();
                if (vista.findViewById(R.id.notice_container_right) != null) {
                    getActivity().getSupportFragmentManager().executePendingTransactions();
                    noticeDetailFragment.mostrarNoticia(noticias.get(0));
                }

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
