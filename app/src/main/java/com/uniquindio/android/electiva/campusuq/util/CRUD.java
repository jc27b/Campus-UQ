package com.uniquindio.android.electiva.campusuq.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;
import com.uniquindio.android.electiva.campusuq.vo.Sugerencia;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
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


    // Sugerencia


    /**
     * Se encarga de consumir el listado de sugerencias desde el servicio
     * @return Las sugerencias alojadas en el servicio
     */
    public static ArrayList<Sugerencia> getListaDeSugerencias(){

        ArrayList<Sugerencia> sugerencias = new ArrayList<>();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(Utilidades.URL_SERVICIO_SUGERENCIA);
        request.setHeader("content-type", "application/json");

        try {

            HttpResponse resp = httpClient.execute(request);
            String respStr = EntityUtils.toString(resp.getEntity());

            Gson gson = new Gson();
            Type tipoListaFilms = new TypeToken<ArrayList<Sugerencia>>(){}.getType();

            sugerencias = gson.fromJson(respStr, tipoListaFilms);

        } catch (Exception e) {
            Log.v(CRUD.class.getSimpleName(), e.getMessage());
            return null;
        }

        return sugerencias;
    }

    /**
     * Permite agregar una sugerencia en el servicio
     * @param jsonSugerencia Json la sugerencia que se desea agregar
     * @return Sugerencia agregada
     */
    public static Sugerencia agregarSugerenciaAlServicio(String jsonSugerencia) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(Utilidades.URL_SERVICIO_SUGERENCIA);
        post.setHeader("content-type", "application/json");
        Sugerencia sugerencia = null;
        try {
            StringEntity entity = new StringEntity(jsonSugerencia);
            post.setEntity(entity);
            HttpResponse respose = httpClient.execute(post);
            String resp = EntityUtils.toString(respose.getEntity());
            sugerencia = Utilidades.convertirJSONASugerencia(resp);
        } catch (Exception e) {
            Log.e("ServicioRest", "Error! insercion de sugerencia " + e.getMessage());
            return null;
        }
        return sugerencia;
    }




}