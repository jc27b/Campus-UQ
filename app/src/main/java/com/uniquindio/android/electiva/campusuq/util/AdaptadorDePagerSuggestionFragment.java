package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.MailboxFragment;
import com.uniquindio.android.electiva.campusuq.fragments.SuggestionFragment;

import java.util.ArrayList;

/**
 * Adaptador de tipo FragmentPager, el cual utilizará fragmentos
 * para mostrar cada una de las páginas de un view pager. Se encarga
 * de gestionar la lista de páginas, conociendo el tamaño de ésta,
 * configurando el fragmento que deberá ir en cada posición y
 * también el título de cada página.
 */
public class AdaptadorDePagerSuggestionFragment extends FragmentPagerAdapter {

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    private ArrayList<String> listaDeTiulos;

    /**
     * Constructor público del adaptador que asigna
     * el manejador de fragmentos que utilizará, y
     * crea una lista de títulos para las páginas
     * que gestionará, para lo cual utiliza el contexto.
     * @param fm Manejador de fragmentos que utilizará el adaptador para asignar fragmentos como páginas.
     * @param context Contexto para cargar los recursos.
     */
    public AdaptadorDePagerSuggestionFragment(FragmentManager fm, Context context) {
        super(fm);
        listaDeTiulos = new ArrayList<>();
        listaDeTiulos.add(context.getResources().getString(R.string.sugerencias));
        listaDeTiulos.add(context.getResources().getString(R.string.buzon));
    }

    /**
     * Metodo que retorna un fragmento con los datos configurados
     * que servirá como página del view pager según la posición que
     * se manda como parámetro. El fragmento es creado por el método
     * newInstance.
     * @param position Posición en la lista de páginas.
     * @return Fragmento para visualizar una página.
     */
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = SuggestionFragment.newInstance();
                break;
            case 1:
                fragment = MailboxFragment.newInstance();
                break;
        }

        return fragment;

    }

    /**
     * Metodo que retorna el número de
     * elementos que maneja el adaptador,
     * el cual es el mismo que el numero
     * de elementos de la lista de titulos.
     * @return Número de elementos del adaptador
     */
    @Override
    public int getCount() {
        return listaDeTiulos.size();
    }

    /**
     * Metodo que retorna el titulo que deberá
     * utilizar cada pagina que muestre el adaptador,
     * segun la posición pasada por parámetro.
     * @param position Posición de la página.
     * @return Título para la página.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return listaDeTiulos.get(position);
    }

    /**
     * Método llamado cuando se va a instanciar el fragmento,
     * que además registra la instancia en un arreglo.
     * @param container Contenedor para el fragmneto.
     * @param position Posición del fragmneto.
     * @return Fragmento instanciado.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Método llamado cuando el fragmento se va a destruir,
     * que además remueve la instancia el arreglo de fragmentos.
     * @param container Contenedor para el framneto.
     * @param position Posición del fragmneto.
     * @param object Fragmento a destruir.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    /**
     * Método que devuelve un fragmento instanciado del
     * arreglo según la posición pasada por parámetro.
     * @param position Posición del fragmento.
     * @return Instancia del fragmento.
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    /**
     * Método que permite obtener el arreglo de instancias de fragmnetos.
     * @return Arreglo de fragmentos.
     */
    public SparseArray<Fragment> getRegisteredFragments() {
        return registeredFragments;
    }

    /**
     * Método asignar un arreglo de instancias de fragmentos.
     * @param registeredFragments Arreglo de fragmentos.
     */
    public void setRegisteredFragments(SparseArray<Fragment> registeredFragments) {
        this.registeredFragments = registeredFragments;
    }

}
