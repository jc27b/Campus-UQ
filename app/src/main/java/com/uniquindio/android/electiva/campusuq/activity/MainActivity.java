package com.uniquindio.android.electiva.campusuq.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeDetailFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeFragment;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDePagerFragment;

public class MainActivity extends AppCompatActivity implements NoticeFragment.OnNoticiaSeleccionadaListener {

    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimationActivity.fa.finish();
        posicion = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        AdaptadorDePagerFragment adapter = new AdaptadorDePagerFragment(getSupportFragmentManager(), getResources().getConfiguration().orientation);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (position == 0) {
                        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                        AdaptadorDePagerFragment adapter = (AdaptadorDePagerFragment) viewPager.getAdapter();
                        Fragment fragment = adapter.getRegisteredFragment(0);
                        changeLayoutConfiguration(fragment, 50, 50);

                    }
                }
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

        NoticeFragment noticeFragment = (NoticeFragment) getSupportFragmentManager().findFragmentByTag("noticeFragment");
        NoticeDetailFragment noticeDetailFragment = (NoticeDetailFragment) getSupportFragmentManager().findFragmentByTag("noticeDetailFragment");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            AdaptadorDePagerFragment adapter = (AdaptadorDePagerFragment) viewPager.getAdapter();
            if (viewPager.getCurrentItem() == 0) {
                Fragment fragment = adapter.getRegisteredFragment(0);
                changeLayoutConfiguration(fragment, 50, 50);

            }

            fragmentTransaction.show(noticeDetailFragment);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            noticeDetailFragment.mostrarNoticia(noticeFragment.getNoticias().get(posicion));

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            AdaptadorDePagerFragment adapter = (AdaptadorDePagerFragment) viewPager.getAdapter();
            if (viewPager.getCurrentItem() == 0) {
                Fragment fragment = adapter.getRegisteredFragment(0);
                changeLayoutConfiguration(fragment, 100, 0);

            }

            fragmentTransaction.hide(noticeDetailFragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            AdaptadorDePagerFragment adapter = (AdaptadorDePagerFragment) viewPager.getAdapter();
            if (viewPager.getCurrentItem() == 0) {
                Fragment fragment = adapter.getRegisteredFragment(0);
                changeLayoutConfiguration(fragment, 100, 0);

            }

        }

    }

    @Override
    public void onNoticiaSeleccionada(int position) {
        posicion = position;

        NoticeFragment noticeFragment = (NoticeFragment) getSupportFragmentManager().findFragmentByTag("noticeFragment");
        NoticeDetailFragment noticeDetailFragment = (NoticeDetailFragment) getSupportFragmentManager().findFragmentByTag("noticeDetailFragment");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            AdaptadorDePagerFragment adapter = (AdaptadorDePagerFragment) viewPager.getAdapter();
            Fragment fragment = adapter.getRegisteredFragment(0);
            changeLayoutConfiguration(fragment, 0, 100);


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(noticeFragment);
            fragmentTransaction.show(noticeDetailFragment);
            fragmentTransaction.addToBackStack("mostrarNoticeDetailFragment");
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();

        }

        noticeDetailFragment.mostrarNoticia(noticeFragment.getNoticias().get(position));

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
            Toast.makeText(this, "Opción 2", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menu_cambiar_idioma) {
            Toast.makeText(this, "Opción 3", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    public void changeLayoutConfiguration(Fragment fragment, int weightLeft, int weightRight) {
        LinearLayout layoutLeft = (LinearLayout) fragment.getView().findViewById(R.id.container_left);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutLeft.getLayoutParams();
        if (params.weight != weightLeft) {
            params.weight = weightLeft;
            layoutLeft.setLayoutParams(params);
        }
        LinearLayout layoutRight = (LinearLayout) fragment.getView().findViewById(R.id.container_right);
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutRight.getLayoutParams();
        if (params2.weight != weightRight) {
            params2.weight = weightRight;
            layoutRight.setLayoutParams(params2);
        }
    }

    /**
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        AdaptadorDePagerFragment adapter = (AdaptadorDePagerFragment) viewPager.getAdapter();
        outState.putSparseParcelableArray("SparseArray", (SparseArray<? extends Parcelable>) adapter.getRegisteredFragments());

    }
    **/
}
