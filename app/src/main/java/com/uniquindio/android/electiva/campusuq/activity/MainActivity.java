package com.uniquindio.android.electiva.campusuq.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryContainerFragment;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryDetailFragment;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeDetailFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeFragment;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDeContacto;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDePagerFragment;
import com.uniquindio.android.electiva.campusuq.util.CustomViewPager;
import com.uniquindio.android.electiva.campusuq.util.Utilidades;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;

/**
 * Actividad principal que contendrá un tab layout y un view pager para
 * proporcionar la navegabilidad por las distintas funciones de la aplicación.
 * En el view pager se colocan fragmentos que contendrán áreas funcionales
 * y se podrá cambiar su disposición al cambiar la orientación. La actividad
 * tiene comunicación con los fragmentos para poder manejar adecuadamente
 * los eventos, y registra un broadcast receiver para detectar cambios
 * en la conectividad a internet.
 */
public class MainActivity extends AppCompatActivity implements NoticeFragment.OnNoticiaSeleccionadaListener, DirectoryFragment.OnDependenciaSeleccionadaListener, DirectoryDetailFragment.OnContactoSeleccionadoListener {

    private static final String POSICION_NOTICIA = "posicion_noticia";
    private static final String POSICION_DEPENDENCIA = "posicion_dependencia";
    private static final String ULTIMO_ITEM_SELECCIONADO = "ultimo_item_seleccionado";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final String ACTIVITY_OPTION_TYPE = "option_type";

    private int posicionNoticia;
    private int posicionDependencia;
    private int lastItemSelected;

    private BroadcastReceiver connectivityReceiver;
    private IntentFilter intentFilter;

    private ViewGroup actionToolBar;

    private CustomViewPager viewPager;

    /**
     * Método llamado cuando se crea la instancia. Se encarga de finalizar
     * la activida de animación, restaurar datos, inicializar y configurar
     * la tool bar, el tab layout, el view pager y su adaptador, y por
     * último crea un intent para el broadcast receiver.
     * @param savedInstanceState Instancia guardada para restaurar los datos.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utilidades.obtenerLenguaje(this);

        setContentView(R.layout.activity_main);

        AnimationActivity.firstActivity.finish();

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

        AdaptadorDePagerFragment adapter = new AdaptadorDePagerFragment(getSupportFragmentManager(), getBaseContext());

        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
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

        actionToolBar = findActionBar(this);

        TabLayout tabLayout = (TabLayout) actionToolBar.findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

    }

    /**
     * Método llamado cuando se cambia de orientación el dispositivo.
     * Quita de la pila los cambios en los fragmentos, reconfigura el
     * view pager y le devuelve el estado anterior al asignar la
     * página seleccionada y el elemento seleccionado.
     * @param newConfig Nueva configuración.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Utilidades.obtenerLenguaje(this);
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            for (int i = 0; i < count; ++i) {
                fm.popBackStack();
            }
        }
        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new AdaptadorDePagerFragment(getSupportFragmentManager(), getBaseContext()));
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

    /**
     * Manejador del evento selección de una noticia,
     * el cual guarda la posición, y dependiendo de
     * la orientación cambia la vista para mostrar
     * la información de la noticia seleccionada.
     * @param position Posición del ítem.
     */
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

    /**
     * Manejador del evento selección de una dependencia,
     * el cual guarda la posición, y dependiendo de
     * la orientación cambia la vista para mostrar
     * la información de la dependencia seleccionada.
     * @param position Posición del ítem.
     */
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

    /**
     * Manejador del evento selección de un contacto,
     * el cual inicia una llamada al teléfono y
     * extensión del respectivo contacto, y pide
     * permiso para hacerlo directamente.
     * @param position Posición del ítem.
     */
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

    /**
     * Método que sirve para buscar la barra de acción
     * de una actividad pasada por parámetro.
     * @param activity Actividad que contiene la barra de acción.
     * @return
     */
    public static ViewGroup findActionBar(Activity activity) {
        int id = activity.getResources().getIdentifier("action_bar", "id", "android");
        ViewGroup actionBar = null;
        if (id != 0) {
            actionBar = (ViewGroup) activity.findViewById(id);
        }
        if (actionBar == null) {
            actionBar = findToolbar((ViewGroup) activity.findViewById(android.R.id.content).getRootView());
        }
        return actionBar;
    }

    /**
     * Método que busca una tool bar en un grupo de vistas
     * pasado por parámetro, de acuerdo a las librerías
     * que esté usando la aplicación.
     * @param viewGroup Grupo de vistas que contiene la tool bar.
     * @return Grupo de vistas correspondiente a la tool bar.
     */
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

    /**
     * Método que se encarga de incluir un
     * archivo de menú en el menú que usa
     * la aplicación en la tool bar.
     * @param menu Menú de la aplicación.
     * @return Éxito de la operación.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    /**
     * Método que se encarga de definir la funcionalidad
     * a ejecutar cuando se presiona un ítem del menú.
     * @param item Ítem presionado.
     * @return Hay cambios en el comportamiento o no.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_iniciar_sesion) {
            Utilidades.mostrarDialigoAgregarPelicula(getSupportFragmentManager(), MainActivity.class.getSimpleName());
        }
        if (id == R.id.menu_ir_a_pagina_universidad) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uniquindio.edu.co/"));
            startActivity(intent);
        }
        if (id == R.id.menu_cambiar_idioma) {
            Utilidades.cambiarIdioma(getBaseContext());
            Intent intent = getIntent();
            finish();
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método utilizado para comprobar si hay
     * conexión a internet o no.
     * @param context Contexto de la aplicación.
     * @return Hay conexión o no.
     */
    public static boolean haveNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean isConnected = netInfo != null && netInfo.isConnected();
        return isConnected;
    }

    /**
     * Método usado para guardar el estado de la actividad
     * cuando ésta puede ser destruida.
     * @param outState Bundle en el que se guardarán datos.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSICION_NOTICIA, posicionNoticia);
        outState.putInt(POSICION_DEPENDENCIA, posicionDependencia);
        outState.putInt(ULTIMO_ITEM_SELECCIONADO, lastItemSelected);
    }

    /**
     * Método llamado cuando se le devuelve el foco
     * a la actividad, se encarga de registrar el
     * broadcast receiver para detectar cambios
     * en la conectividad.
     */
    @Override
    protected void onResume() {
        super.onResume();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("ConnectivityReceiver", "action -> " + intent.getAction());
                Log.d("HaveNetworkConnection", ""+haveNetworkConnection(context));
                TextView textView = (TextView) findViewById(R.id.text_no_connection);
                CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
                if (!haveNetworkConnection(context)) {
                    textView.setVisibility(View.VISIBLE);
                    viewPager.setPagingEnabled(false);
                    viewPager.setCurrentItem(1);
                    DirectoryContainerFragment page = (DirectoryContainerFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                    page.setImageButtonEnabled(false);
                } else {
                    textView.setVisibility(View.GONE);
                    viewPager.setPagingEnabled(true);
                    viewPager.setCurrentItem(1);
                    DirectoryContainerFragment page = (DirectoryContainerFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                    page.setImageButtonEnabled(true);
                }

            }
        };
        registerReceiver(connectivityReceiver, intentFilter);
    }

    /**
     * Método llamado cuando la actividad pierde
     * el foco, quita el registro del broadcast
     * receiver pues no se podrá usar.
     */
    @Override
    protected void onPause() {
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver);
            connectivityReceiver = null;
        }
        super.onPause();
    }

    /**
     * Método llamado cuando la actividad será
     * destruida, quita el registro del broadcast
     * receiver pues no se podrá usar.
     */
    @Override
    protected void onDestroy() {
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver);
            connectivityReceiver = null;
        }
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
