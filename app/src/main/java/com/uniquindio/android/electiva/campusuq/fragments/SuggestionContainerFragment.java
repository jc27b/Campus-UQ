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
 * A simple {@link Fragment} subclass.
 */
public class SuggestionContainerFragment extends Fragment {

    private int lastItemSelected;

    public SuggestionContainerFragment() {

    }

    public static SuggestionContainerFragment newInstance() {

        SuggestionContainerFragment fragment = new SuggestionContainerFragment();

        fragment.setRetainInstance(true);

        fragment.setLastItemSelected(0);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_suggestion_container, container, false);

        AdaptadorDePagerSuggestionFragment adapter = new AdaptadorDePagerSuggestionFragment(getChildFragmentManager());

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

    public int getLastItemSelected() {
        return lastItemSelected;
    }

    public void setLastItemSelected(int lastItemSelected) {
        this.lastItemSelected = lastItemSelected;
    }

}