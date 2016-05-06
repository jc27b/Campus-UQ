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

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

                for (int i = 1; i <= 7; i++) {
                    Bitmap imagen = BitmapFactory.decodeResource(getContext().getResources(), ((int) (Math.random()*2)) == 1 ? R.drawable.noticias : R.drawable.detalle_noticia);
                    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
                    String date = df.format(Calendar.getInstance().getTime());
                    Noticia noticia = new Noticia(imagen,"Noticia "+i,"ID: "+i,date,"Detalle de la noticia "+i);
                    noticias.add(noticia);
                }

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
