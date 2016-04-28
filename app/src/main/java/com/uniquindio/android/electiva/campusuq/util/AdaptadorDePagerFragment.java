package com.uniquindio.android.electiva.campusuq.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryContainerFragment;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeContainerFragment;
import com.uniquindio.android.electiva.campusuq.fragments.SuggestionFragment;

import java.util.ArrayList;

public class AdaptadorDePagerFragment extends FragmentPagerAdapter {

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    private ArrayList<String> listaDeTiulos;

    public AdaptadorDePagerFragment(FragmentManager fm) {
        super(fm);
        listaDeTiulos = new ArrayList<>();
        listaDeTiulos.add("Noticias");
        listaDeTiulos.add("Directorio");
        listaDeTiulos.add("Sugerencias");
    }

    @Override
    public Fragment getItem(int position) {
        SuggestionFragment f = null;
        SuggestionFragment.mostrarMensajeLog("pos: "+position);

        switch (position) {
            case 0:
                NoticeContainerFragment noticeContainerFragment = NoticeContainerFragment.newContainerInstance();
                return noticeContainerFragment;
            case 1:
                DirectoryContainerFragment directoryContainerFragment = DirectoryContainerFragment.newContainerInstance();
                return directoryContainerFragment;
            case 2:
                f = SuggestionFragment.newInstance(R.color.android_green, position+1);
                break;
        }

        return f;
    }

    @Override
    public int getCount() {
        return listaDeTiulos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listaDeTiulos.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    public SparseArray<Fragment> getRegisteredFragments() {
        return registeredFragments;
    }

    public void setRegisteredFragments(SparseArray<Fragment> registeredFragments) {
        this.registeredFragments = registeredFragments;
    }
}