package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.LoginFragment;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.Locale;

/**
 * Clase de utilidades que sirve para cambiar el lenguaje
 * con el que se mostrará la aplicación y mostrar un
 * fragmento de tipo dialogo para iniciar sesión.
 */
public class Utilidades {

    public final static String MIS_PREFERENCIAS = "MisPreferencias";
    public final static String LENGUAJE_DE_PREFERENCIA = "languaje_preferences";
    public final static String LENGUAJE_ES = "es";
    public final static String LENGUAJE_EN = "en";

    public final static String URL_SERVICIO_DEPENDENCIA = "http://campusuq-jc27b.rhcloud.com/dependencia";
    public final static String URL_SERVICIO_NOTICIA = "http://campusuq-jc27b.rhcloud.com/noticia";
    public static final int LISTAR_DEPENDENCIAS = 1;
    public static final int MODIFICAR_DEPENDENCIA = 2;
    public static final int LISTAR_NOTICIAS = 3;

    public static final String NOMBRE_BD = "Campus_UQ";
    public static final String NOMBRE_TABLA_CONTACTO = "Contacto";
    public static final String CAMPOS_TABLA_CONTACTO[] = new String[]{"_ID", "DEPENDENCIA", "NOMBRE", "TELEFONO", "EXTENSION"};
    public static final String NOMBRE_TABLA_DEPENDENCIA = "Dependencia";
    public static final String CAMPOS_TABLA_DEPENDENCIA[] = new String[]{"_ID","IMAGEN", "NOMBRE"};


    /**
     * Es el encargado de mostrar el dialogo por medio del cual se va a iniciar sesión
     * @param fragmentManager permite realizar la transacción del diálogo
     * @param nameClass nombre de la actividad que lo invoco
     */
    public static void mostrarDialigoAgregarPelicula(FragmentManager fragmentManager, String nameClass) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setStyle(loginFragment.STYLE_NORMAL, R.style.MiDialogo);
        loginFragment.show(fragmentManager, nameClass);
    }

    /**
     * Metodo que permite a la aplicaciòn cambiar de idioma,
     * para ello utiliza las preferencias compartidas accedidas
     * en modo privado, las edita y hace uso del metodo
     * obtener lenguaje.
     * @param context El contexto del fragmento lista de peliculas
     */
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

    /**
     * Metodo que permite que la aplicacion se configuere con
     * el idioma que tenga el dispositivo donde este siendo usado,
     * para esto hace uso del locale.
     * @param context Contexto del fragmento lista peliculas
     */
    public static void obtenerLenguaje(Context context){

        SharedPreferences prefs = context.getSharedPreferences(MIS_PREFERENCIAS,context.MODE_PRIVATE);
        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA, LENGUAJE_ES);

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        context.getApplicationContext().getResources().updateConfiguration(config, null);

    }

    /**
     * Se encarga de converir un String formato JSON a una Película
     * @param jsonDependencia string en formato JSON
     * @return pelicula resultante de la conversión
     */
    public static Dependencia convertirJSONADependencia(String jsonDependencia)
    {
        Gson gson = new Gson();
        Dependencia dependencia = gson.fromJson(jsonDependencia, Dependencia.class);
        return dependencia;
    }

    /**
     * Se encarga de convertir una pelicula en un JSON
     * @param dependencia pelicula que se desea transformar
     * @return cadena en formato de json de pélicula
     */
    public static String convertirDependenciaAJSON(Dependencia dependencia) {
        Gson gson = new Gson();
        String json = gson.toJson(dependencia);
        return json;
    }

    /**
     * Se encarga de mostrar un mensaje en pantalla
     * @param mensaje mensaje que se quiere enseñar
     */
    public static void mostrarMensaje(String mensaje, Context context){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Se encarga de mostrar un mensaje por consola
     * @param mensaje mensaje a mostrar
     */
    public static void mostrarMensajeConsola(String mensaje) {
        if (mensaje.length() > 4000) {
            Log.v(Utilidades.class.getSimpleName(), mensaje.substring(0, 4000));
            mostrarMensajeConsola(mensaje.substring(4000));
        } else {
            Log.v(Utilidades.class.getSimpleName(), mensaje);
        }
    }


}
