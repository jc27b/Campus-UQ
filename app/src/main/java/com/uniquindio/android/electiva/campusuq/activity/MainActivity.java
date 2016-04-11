package com.uniquindio.android.electiva.campusuq.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.NoticeFragment;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimationActivity.fa.finish();

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            NoticeFragment noticeFragment = new NoticeFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            noticeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, noticeFragment, "noticeFragment");
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

        NoticeFragment fragment = (NoticeFragment) getFragmentManager().findFragmentByTag("noticeFragment");

        int id = item.getItemId();

        if (id == R.id.menu_agregar) {
            fragment.getNoticias().add(0, new Noticia("Nueva noticia"));
            fragment.getAdaptador().notifyItemInserted(0);
        }
        if (id == R.id.menu_eliminar) {
            fragment.getNoticias().remove(0);
            fragment.getAdaptador().notifyItemRemoved(0);
        }
        if (id == R.id.menu_modificar) {
            Noticia aux = fragment.getNoticias().get(1);
            fragment.getNoticias().set(1, fragment.getNoticias().get(2));
            fragment.getNoticias().set(2, aux);

            fragment.getAdaptador().notifyItemMoved(1, 2);
        }


        return super.onOptionsItemSelected(item);
    }

}
