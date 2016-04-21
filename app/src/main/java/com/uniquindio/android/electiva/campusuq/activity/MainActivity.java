package com.uniquindio.android.electiva.campusuq.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeDetailFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeFragment;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoticeFragment.OnNoticiaSeleccionadaListener {

    private ArrayList<Noticia> noticias;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            AnimationActivity.fa.finish();

            posicion = 0;

            noticias = new ArrayList<Noticia>();

            noticias.add(new Noticia("Noticia 1"));
            noticias.add(new Noticia("Noticia 2"));
            noticias.add(new Noticia("Noticia 3"));

            // Create a new Fragment to be placed in the activity layout
            NoticeFragment noticeFragment = new NoticeFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            noticeFragment.setArguments(getIntent().getExtras());
            noticeFragment.setNoticias(noticias);

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, noticeFragment, "noticeFragment");
            fragmentTransaction.commit();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                NoticeDetailFragment noticeDetailFragment = new NoticeDetailFragment();
                fragmentTransaction.add(R.id.fragment_container, noticeDetailFragment, "noticeDetailFragment");
                fragmentTransaction.commit();
                fragmentManager.executePendingTransactions();
                noticeDetailFragment.mostrarNoticia(noticias.get(posicion));
            }


        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        NoticeDetailFragment noticeDetailFragment = (NoticeDetailFragment) getSupportFragmentManager().findFragmentByTag("noticeDetailFragment");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            boolean esFragmento = noticeDetailFragment != null;
            if (!esFragmento) {
                noticeDetailFragment = new NoticeDetailFragment();
                noticeDetailFragment.setArguments(getIntent().getExtras());
            }
            fragmentTransaction.add(R.id.fragment_container, noticeDetailFragment, "noticeDetailFragment");
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            noticeDetailFragment.mostrarNoticia(noticias.get(posicion));

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            fragmentTransaction.remove(noticeDetailFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoticiaSeleccionada(int position) {
        posicion = position;

        NoticeDetailFragment noticeDetailFragment = (NoticeDetailFragment) getSupportFragmentManager().findFragmentByTag("noticeDetailFragment");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            boolean esFragmento = noticeDetailFragment != null;
            if (!esFragmento) {
                noticeDetailFragment = new NoticeDetailFragment();
                noticeDetailFragment.setArguments(getIntent().getExtras());
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, noticeDetailFragment, "noticeDetailFragment");
            fragmentTransaction.addToBackStack("noticeDetailFragment");
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        }

        noticeDetailFragment.mostrarNoticia(noticias.get(position));

    }

}
