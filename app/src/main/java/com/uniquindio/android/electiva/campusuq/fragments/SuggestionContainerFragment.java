package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.AdaptadorDePagerSuggestionFragment;

/**
 * Fragmento de la página de sugerencias en el viewpager,
 * que contendrá otro view pager para los fragmentos
 * de sugerencias y buzón.
 */
public class SuggestionContainerFragment extends Fragment {

    private int lastItemSelected;

    /**
     * Constructor vacío para instanciar el fragmento.
     */
    public SuggestionContainerFragment() {

    }

    /**
     * Creador de una nueva instancia del fragmento.
     * @return Nueva instancia del fragmento.
     */
    public static SuggestionContainerFragment newInstance() {

        SuggestionContainerFragment fragment = new SuggestionContainerFragment();

        fragment.setRetainInstance(true);

        fragment.setLastItemSelected(0);

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
     * Método que configura la vista que utilizará el fragmento.
     * Configura el view pager y el tab layout que contiene, para
     * poder así mostrar los fragmentos de sugerencias y buzón.
     * @param inflater Objeto para inflar la vista del fragmento.
     * @param container Grupo de vistas padre en el que se inserta la vista.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     * @return Vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_suggestion_container, container, false);

        AdaptadorDePagerSuggestionFragment adapter = new AdaptadorDePagerSuggestionFragment(getChildFragmentManager(), getContext());

        ViewPager viewPager = (ViewPager) vista.findViewById(R.id.viewpager2);
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

        TabLayout tabLayout = (TabLayout) vista.findViewById(R.id.tab_layout_2);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        return vista;
    }

    /**
     * Método para obtener la posición de la última
     * página seleccionada del view pager.
     * @return Posición de página seleccionada.
     */
    public int getLastItemSelected() {
        return lastItemSelected;
    }

    /**
     * Método para asignar la posición de
     * la página del view pager que
     * será mostrada.
     * @param lastItemSelected Posición de la página.
     */
    public void setLastItemSelected(int lastItemSelected) {
        this.lastItemSelected = lastItemSelected;
    }

}