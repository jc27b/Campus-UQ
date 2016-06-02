package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uniquindio.android.electiva.campusuq.vo.Sugerencia;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Clase de utilidades que sirve para cambiar el lenguaje
 * con el que se mostrará la aplicación y mostrar un
 * fragmento de tipo dialogo para iniciar sesión.
 */
public class Utilidades {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final String TWITTER_KEY = "sDB3C93DfyHAnH33sMYpHp76j";
    public static final String TWITTER_SECRET = "TD2Q2AiuxlkOmlY3SlWYihd9dyg9jmE38ktphkvcdFml0ZDGrm";

    public final static String MIS_PREFERENCIAS = "MisPreferencias";
    public final static String LENGUAJE_DE_PREFERENCIA = "languaje_preferences";
    public final static String LENGUAJE_ES = "es";
    public final static String LENGUAJE_EN = "en";

    public final static String URL_SERVICIO_DEPENDENCIA = "http://campusuq-jc27b.rhcloud.com/dependencia";
    public final static String URL_SERVICIO_NOTICIA = "http://campusuq-jc27b.rhcloud.com/noticia";
    public final static String URL_SERVICIO_SUGERENCIA = "http://campusuq-jc27b.rhcloud.com/sugerencia";
    public static final int LISTAR_DEPENDENCIAS = 1;
    public static final int LISTAR_NOTICIAS = 2;
    public static final int LISTAR_SUGERENCIAS = 3;
    public static final int AGREGAR_SUGERENCIA = 4;
    public static final String NO_SE_AGREGO_LA_SUGERENCIA = "No se logró agregar la sugerencia";

    public static final String NOMBRE_BD = "Campus_UQ";
    public static final String NOMBRE_TABLA_CONTACTO = "Contacto";
    public static final String CAMPOS_TABLA_CONTACTO[] = new String[]{"_ID", "DEPENDENCIA", "NOMBRE", "TELEFONO", "EXTENSION"};
    public static final String NOMBRE_TABLA_DEPENDENCIA = "Dependencia";
    public static final String CAMPOS_TABLA_DEPENDENCIA[] = new String[]{"_ID","IMAGEN", "NOMBRE"};
    public static final String NOMBRE_TABLA_SUGERENCIA = "Sugerencia";
    public static final String CAMPOS_TABLA_SUGERENCIA[] = new String[]{"_ID", "ASUNTO", "DETALLE", "IMAGEN", "ESTADO"};

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
     * Se encarga de converir un String formato JSON a una Sugerencia
     * @param jsonSugerencia string en formato JSON
     * @return sugerencia resultante de la conversión
     */
    public static Sugerencia convertirJSONASugerencia(String jsonSugerencia)
    {
        Gson gson = new Gson();
        Sugerencia sugerencia = gson.fromJson(jsonSugerencia, Sugerencia.class);
        return sugerencia;
    }

    /**
     * Se encarga de convertir una sugerencia en un JSON
     * @param sugerencia sugerencia que se desea transformar
     * @return cadena en formato de json de sugerencia
     */
    public static String convertirSugerenciaAJSON(Sugerencia sugerencia) {
        Gson gson = new Gson();
        String json = gson.toJson(sugerencia);
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

    /**
     * Método para generar el key hash de la apliación de facebook.
     * @param context Contexto de la aplicación.
     */
    public static void getKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("Mi clave HASH:", sign);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("prueba", "1 KeyHash Error: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.d("prueba", "2 KeyHash Error: " + e.getMessage());
        }

    }

}
