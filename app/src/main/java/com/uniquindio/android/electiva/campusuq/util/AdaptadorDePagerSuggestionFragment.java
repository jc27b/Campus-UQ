package com.uniquindio.android.electiva.campusuq.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.campusuq.fragments.MailboxFragment;
import com.uniquindio.android.electiva.campusuq.fragments.SuggestionFragment;

import java.util.ArrayList;

public class AdaptadorDePagerSuggestionFragment extends FragmentPagerAdapter {

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    private ArrayList<String> listaDeTiulos;

    public AdaptadorDePagerSuggestionFragment(FragmentManager fm) {
        super(fm);
        listaDeTiulos = new ArrayList<>();
        listaDeTiulos.add("Sugerencias");
        listaDeTiulos.add("Buzon");
    }

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
