package com.uniquindio.android.electiva.campusuq.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailboxFragment extends Fragment {

    public MailboxFragment() {
        // Required empty public constructor
    }

    public static MailboxFragment newInstance() {

        MailboxFragment fragment = new MailboxFragment();

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mailbox, container, false);

        GridLayout gridLayout = (GridLayout) vista.findViewById(R.id.grid_layout);

        ArrayList<String> sugerencias = new ArrayList<String>();
        sugerencias.add("Sugerencia 1");
        sugerencias.add("Sugerencia 2");
        sugerencias.add("Sugerencia 3");
        sugerencias.add("Sugerencia 4");
        sugerencias.add("Sugerencia 5");
        sugerencias.add("Sugerencia 6");
        sugerencias.add("Sugerencia 7");

        int veinteDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getContext().getResources().getDisplayMetrics());

        for (int i = 0; i < sugerencias.size(); i++) {

            TextView asunto = new TextView(getContext());
            asunto.setText(sugerencias.get(i));
            asunto.setTextSize(veinteDP);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(0));
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.rightMargin = veinteDP*4;
            param.leftMargin = veinteDP*4;
            asunto.setLayoutParams(param);
            gridLayout.addView(asunto);

            CheckBox checkBox1 = new CheckBox(getContext());
            checkBox1.setChecked(((int) (Math.random()*2)) == 1 ? true : false);
            GridLayout.LayoutParams param2 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(1));
            param2.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param2.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param2.rightMargin = veinteDP*4;
            checkBox1.setLayoutParams(param2);
            gridLayout.addView(checkBox1);

            CheckBox checkBox2 = new CheckBox(getContext());
            checkBox2.setChecked(((int) (Math.random()*2)) == 1 ? true : false);
            GridLayout.LayoutParams param3 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(2));
            param3.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param3.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param3.rightMargin = veinteDP*4;
            checkBox2.setLayoutParams(param3);
            gridLayout.addView(checkBox2);

            CheckBox checkBox3 = new CheckBox(getContext());
            checkBox3.setChecked(((int) (Math.random()*2)) == 1 ? true : false);
            GridLayout.LayoutParams param4 = new GridLayout.LayoutParams(GridLayout.spec(i+1), GridLayout.spec(3));
            param4.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param4.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param4.rightMargin = veinteDP*4;
            checkBox3.setLayoutParams(param4);
            gridLayout.addView(checkBox3);

        }

        return vista;
    }

}
