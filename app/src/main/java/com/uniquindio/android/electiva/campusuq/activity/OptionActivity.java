package com.uniquindio.android.electiva.campusuq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;

public class OptionActivity extends AppCompatActivity {

    private static final String ACTIVITY_OPTION_TYPE = "option_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        TextView txtTitulo = (TextView) findViewById(R.id.txt_titulo);
        final TextView txt1 = (TextView) findViewById(R.id.txt_option_1);
        final TextView txt2 = (TextView) findViewById(R.id.txt_option_2);

        String tipo = getIntent().getExtras().getString(ACTIVITY_OPTION_TYPE);

        if (tipo.equals("Iniciar Sesión")) {
            txtTitulo.setText("Iniciar Sesión");
            txt1.setText("Facebook");
            txt2.setText("Twitter");
        } else if (tipo.equals("Cambiar Idioma")) {
            txtTitulo.setText("Cambiar Idioma");
            txt1.setText("Español");
            txt2.setText("Inglés");
        }

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), txt1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), txt2.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
