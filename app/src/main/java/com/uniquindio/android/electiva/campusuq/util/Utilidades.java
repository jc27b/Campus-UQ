package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.LoginFragment;

import java.util.Locale;

public class Utilidades {

    public final static String MIS_PREFERENCIAS = "MisPreferencias";
    public final static String LENGUAJE_DE_PREFERENCIA = "languaje_preferences";
    public final static String LENGUAJE_ES = "es";
    public final static String LENGUAJE_EN = "en";

    /**
     * es el encargado de mostrar el dialogo por medio del cual se van
     a agregar películas
     *
     * @param fragmentManager permite realizar la transacción del
    dialogo
     * @param nameClass       nombre de la actividad que lo invoco
     */
    public static void mostrarDialigoAgregarPelicula(FragmentManager fragmentManager, String nameClass) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setStyle(loginFragment.STYLE_NORMAL, R.style.MiDialogo);
        loginFragment.show(fragmentManager, nameClass);
    }

    public static void cambiarIdioma(Context context){

        SharedPreferences prefs = context.getSharedPreferences(MIS_PREFERENCIAS, context.MODE_PRIVATE);
        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA, LENGUAJE_ES);

        if(language.equals(LENGUAJE_ES)){
            language = LENGUAJE_EN;
        }
        else if(language.equals(LENGUAJE_EN)){
            language = LENGUAJE_ES;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LENGUAJE_DE_PREFERENCIA, language);
        editor.commit();

        obtenerLenguaje(context);

    }

    public static void obtenerLenguaje(Context context){

        SharedPreferences prefs = context.getSharedPreferences(MIS_PREFERENCIAS,context.MODE_PRIVATE);
        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA, LENGUAJE_ES);

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        context.getApplicationContext().getResources().updateConfiguration(config, null);

    }

}
