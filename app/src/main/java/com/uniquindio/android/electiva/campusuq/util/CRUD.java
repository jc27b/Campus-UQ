package com.uniquindio.android.electiva.campusuq.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;


/**
 * Clase que permite realizar operaciones CRUD
 * mediante el servicio de la aplicación.
 */
public class CRUD {


    // Dependencia


    /**
     * Se encarga de consumir el listado de dependencias desde el servicio
     * @return Las dependencias alojadas en el servicio
     */
    public static ArrayList<Dependencia> getListaDeDependencias(){

        ArrayList<Dependencia> dependencias = new ArrayList<>();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(Utilidades.URL_SERVICIO_DEPENDENCIA);
        request.setHeader("content-type", "application/json");

        try {

            HttpResponse resp = httpClient.execute(request);
            String respStr = EntityUtils.toString(resp.getEntity());

            Gson gson = new Gson();
            Type tipoListaFilms = new TypeToken<ArrayList<Dependencia>>(){}.getType();

            dependencias = gson.fromJson(respStr, tipoListaFilms);

        } catch (Exception e) {
            Log.v(CRUD.class.getSimpleName(), e.getMessage());
            return null;
        }

        return dependencias;
    }


    // Noticia


    /**
     * Se encarga de consumir el listado de noticias desde el servicio
     * @return Las noticias alojadas en el servicio
     */
    public static ArrayList<Noticia> getListaDeNoticias(){

        ArrayList<Noticia> noticias = new ArrayList<>();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(Utilidades.URL_SERVICIO_NOTICIA);
        request.setHeader("content-type", "application/json");

        try {

            HttpResponse resp = httpClient.execute(request);
            String respStr = EntityUtils.toString(resp.getEntity());

            Gson gson = new Gson();
            Type tipoListaFilms = new TypeToken<ArrayList<Noticia>>(){}.getType();

            noticias = gson.fromJson(respStr, tipoListaFilms);

        } catch (Exception e) {
            Log.v(CRUD.class.getSimpleName(), e.getMessage());
            return null;
        }

        return noticias;
    }

    /**
     * Permite agregar una pelicula en el servicio
     * @param jsonPelicula Json la pelicula que se desea agregar
     * @return Pelicula agregada
     */
    /**
    public static Film agregarPeliculaAlServicio(String jsonPelicula) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(Utilidades.URL_SERVICIO);
        post.setHeader("content-type", "application/json");
        Film pelicula = null;
        try {
            StringEntity entity = new StringEntity(jsonPelicula);
            post.setEntity(entity);
            HttpResponse respose = httpClient.execute(post);
            String resp = EntityUtils.toString(respose.getEntity());
            pelicula = Utilidades.convertirJSONAPelicula(resp);
        } catch (Exception e) {
            Log.e("ServicioRest", "Error! insercion de película " + e.getMessage());
            return null;
        }
        return pelicula;
    }
     */

    /**
     * Permite eliminar una pelicula del servicio
     * @param idPelicula ID de la pelicula a eliminar
     * @return Pelicula eliminada
     */
    /**
    public static Film eliminarPeliculaDelServicio(String idPelicula) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpDelete delete = new HttpDelete(Utilidades.URL_SERVICIO+ "/" + idPelicula);
        delete.setHeader("content-type", "application/json");
        try {
            HttpResponse response = client.execute(delete);
            String res = EntityUtils.toString(response.getEntity());
            return Utilidades.convertirJSONAPelicula(res);
        } catch (Exception e) {
            Log.e("ServicioRest", "Error! eliminando la pelicula " + e.getMessage());
            return null;
        }
    }
     */


}