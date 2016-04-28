package com.uniquindio.android.electiva.campusuq.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryDetailFragment;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeDetailFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeFragment;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeContacto;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDePagerFragment;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;

public class MainActivity extends AppCompatActivity implements NoticeFragment.OnNoticiaSeleccionadaListener, DirectoryFragment.OnDependenciaSeleccionadaListener, DirectoryDetailFragment.OnContactoSeleccionadoListener {

    private static final String POSICION_NOTICIA = "posicion_noticia";
    private static final String POSICION_DEPENDENCIA = "posicion_dependencia";
    private static final String ULTIMO_ITEM_SELECCIONADO = "ultimo_item_seleccionado";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private int posicionNoticia;
    private int posicionDependencia;
    private int lastItemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimationActivity.fa.finish();

        if (savedInstanceState == null) {
            posicionNoticia = 0;
            posicionDependencia = 0;
            lastItemSelected = 0;
        } else {
            posicionNoticia = savedInstanceState.getInt(POSICION_NOTICIA);
            posicionDependencia = savedInstanceState.getInt(POSICION_DEPENDENCIA);
            lastItemSelected = savedInstanceState.getInt(ULTIMO_ITEM_SELECCIONADO);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        AdaptadorDePagerFragment adapter = new AdaptadorDePagerFragment(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastItemSelected = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) findActionBar(this).findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            for (int i = 0; i < count; ++i) {
                fm.popBackStack();
            }
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new AdaptadorDePagerFragment(getSupportFragmentManager()));
        viewPager.setCurrentItem(lastItemSelected);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (lastItemSelected == 0) {
                    onNoticiaSeleccionada(posicionNoticia);
                } else if (lastItemSelected == 1) {
                    onDependenciaSeleccionada(posicionDependencia);
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onNoticiaSeleccionada(int position) {
        posicionNoticia = position;

        NoticeFragment noticeFragment = (NoticeFragment) getSupportFragmentManager().findFragmentByTag("noticeFragment");
        NoticeDetailFragment noticeDetailFragment = (NoticeDetailFragment) getSupportFragmentManager().findFragmentByTag("noticeDetailFragment");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            noticeDetailFragment = new NoticeDetailFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.notice_container_left, noticeDetailFragment, "noticeDetailFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();

        }

        noticeDetailFragment.mostrarNoticia(noticeFragment.getNoticias().get(position));

    }

    @Override
    public void onDependenciaSeleccionada(final int position) {
        posicionDependencia = position;

        DirectoryFragment directoryFragment = (DirectoryFragment) getSupportFragmentManager().findFragmentByTag("directoryFragment");
        DirectoryDetailFragment directoryDetailFragment = (DirectoryDetailFragment) getSupportFragmentManager().findFragmentByTag("directoryDetailFragment");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            directoryDetailFragment = new DirectoryDetailFragment();
            directoryDetailFragment.setDependencia(directoryFragment.getDirectorio().get(position).getContactos());
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.directory_container_left, directoryDetailFragment, "directoryDetailFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();

        } else {
            directoryDetailFragment.setDependencia(directoryFragment.getDirectorio().get(position).getContactos());
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecView3);
            recyclerView.setAdapter(new AdaptadorDeContacto(directoryDetailFragment.getDependencia(), directoryDetailFragment));
            recyclerView.invalidate();
        }

    }

    @Override
    public void onContactoSeleccionado(int position) {
        DirectoryDetailFragment directoryDetailFragment = (DirectoryDetailFragment) getSupportFragmentManager().findFragmentByTag("directoryDetailFragment");
        Contacto contacto = directoryDetailFragment.getDependencia().get(position);
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacto.getTelefono().trim()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
        try {
            startActivity(callIntent);
        } catch (SecurityException se) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contacto.getTelefono(), null));
            startActivity(intent);
        }
    }

    public static ViewGroup findActionBar(Activity activity) {
        int id = activity.getResources().getIdentifier("action_bar", "id", "android");
        ViewGroup actionBar = null;
        if (id != 0) {
            actionBar = (ViewGroup) activity.findViewById(id);
        }
        if (actionBar == null) {
            actionBar = findToolbar((ViewGroup) activity.findViewById(android.R.id.content)
                    .getRootView());
        }
        return actionBar;
    }

    private static ViewGroup findToolbar(ViewGroup viewGroup) {
        ViewGroup toolbar = null;
        for (int i = 0, len = viewGroup.getChildCount(); i < len; i++) {
            View view = viewGroup.getChildAt(i);
            if (view.getClass().getName().equals("android.support.v7.widget.Toolbar")
                    || view.getClass().getName().equals("android.widget.Toolbar")) {
                toolbar = (ViewGroup) view;
            } else if (view instanceof ViewGroup) {
                toolbar = findToolbar((ViewGroup) view);
            }
            if (toolbar != null) {
                break;
            }
        }
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_iniciar_sesion) {
            Toast.makeText(this, "Opción 1", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menu_ir_a_pagina_universidad) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uniquindio.edu.co/"));
            startActivity(intent);
        }
        if (id == R.id.menu_cambiar_idioma) {
            Toast.makeText(this, "Opción 3", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSICION_NOTICIA, posicionNoticia);
        outState.putInt(POSICION_DEPENDENCIA, posicionDependencia);
        outState.putInt(ULTIMO_ITEM_SELECCIONADO, lastItemSelected);
    }


}
