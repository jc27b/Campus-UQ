package com.uniquindio.android.electiva.campusuq.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.uniquindio.android.electiva.campusuq.R;

/**
 * Actividad utilizada para mostrar una animación
 * con el logo de la universidad del quindío, luego
 * llama a la actividad principal.
 */
public class AnimationActivity extends AppCompatActivity {

    public static Activity firstActivity;

    /**
     * Método llamado cuando se crea la actividad, se encarga
     * de asignar la visa a la actividad, guardarse en un
     * atributo estático, crear la animación y configurar
     * el comportamiento para llamar a la actividad principal
     * cuando se termine la animación.
     * @param savedInstanceState Instancia guardada para restaurar los datos.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        firstActivity = this;
        final ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        image.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(AnimationActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
