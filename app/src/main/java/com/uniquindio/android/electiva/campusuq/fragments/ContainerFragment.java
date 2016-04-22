package com.uniquindio.android.electiva.campusuq.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends Fragment {

    private static final String TIPO_FRAGMENTO = "TipoDeFragmento";
    private static final String CONFIGURATION = "Configuration";
    private int tipoDeContenedor;
    private int configuration;

    public final Handler handler = new Handler();
    public Runnable runPager;

    public ContainerFragment() {
        // Required empty public constructor
    }

    public static ContainerFragment newNoticeContainerInstance(int configuration) {

        ContainerFragment fragment = new ContainerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(TIPO_FRAGMENTO, 1);
        bundle.putInt(CONFIGURATION, configuration);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.tipoDeContenedor = (getArguments() != null) ? getArguments().getInt(TIPO_FRAGMENTO) : 1;
        this.configuration = (getArguments() != null) ? getArguments().getInt(CONFIGURATION) : Configuration.ORIENTATION_PORTRAIT;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_container, container, false);

        if (configuration == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayout layout = (LinearLayout) vista.findViewById(R.id.container_left);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
            params.weight = 100;
            layout.setLayoutParams(params);
            LinearLayout layoutRight = (LinearLayout) vista.findViewById(R.id.container_right);
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutRight.getLayoutParams();
            params2.weight = 0;
            layoutRight.setLayoutParams(params2);
        }

        if (tipoDeContenedor == 1) {

            final NoticeFragment noticeFragment = new NoticeFragment();

            final ArrayList<Noticia> noticias = new ArrayList<Noticia>();

            noticias.add(new Noticia("Noticia 1"));
            noticias.add(new Noticia("Noticia 2"));
            noticias.add(new Noticia("Noticia 3"));

            noticeFragment.setNoticias(noticias);

            final NoticeDetailFragment noticeDetailFragment = new NoticeDetailFragment();

            runPager = new Runnable() {

                @Override
                public void run()
                {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.container_left, noticeFragment, "noticeFragment");
                    fragmentTransaction.add(R.id.container_right, noticeDetailFragment, "noticeDetailFragment");
                    if (configuration == Configuration.ORIENTATION_PORTRAIT) {
                        fragmentTransaction.hide(noticeDetailFragment);
                    }
                    fragmentTransaction.commit();
                    fragmentManager.executePendingTransactions();
                    noticeDetailFragment.mostrarNoticia(noticias.get(0));
                }
            };
            handler.post(runPager);


        }

        return vista;
    }


    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runPager);
    }

}
