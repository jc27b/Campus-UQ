package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.uniquindio.android.electiva.campusuq.vo.Contacto;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;
import com.uniquindio.android.electiva.campusuq.vo.Sugerencia;

import java.util.ArrayList;

/**
 * Clase que implementa todos los métodos necesarios
 * para crear, leer, actualizar y eliminar contactos,
 * dependencias y sugerencias.
 */
public class CRUDSQL {

    private CampusUQSQLiteHelper usdbh;
    private SQLiteDatabase db;

    /**
     * Constructor que inicializa el ayudante y la base de atos.
     * @param context Contexto de la aplicación.
     * @param version Versión de la base de datos.
     */
    public CRUDSQL(Context context, int version) {
        usdbh = new CampusUQSQLiteHelper(context, Utilidades.NOMBRE_BD , null, version);
        db = usdbh.getWritableDatabase();
    }


    // Contacto


    /**
     * Método que devuelve una sentencia para crear la tabla de contactos.
     * @return Sentencia create para contactos.
     */
    public static String crearTablaContacto(){
        String crearTabla = "CREATE TABLE ? ( ? INTEGER PRIMARY KEY AUTOINCREMENT, ? TEXT, ? TEXT, ? TEXT, ? TEXT)";
        StringBuilder builder = new StringBuilder(crearTabla);
        builder.replace(builder.indexOf("?"), crearTabla.indexOf("?")+1,Utilidades.NOMBRE_TABLA_CONTACTO);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_CONTACTO[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_CONTACTO[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_CONTACTO[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_CONTACTO[3]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_CONTACTO[4]);
        return builder.toString();
    }

    /**
     * Método que recupera una lista con todos los contactos de la base de datos.
     * @return Contactos almacenados en la db.
     */
    public ArrayList<Contacto> getContactos() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        Cursor c = db.query(Utilidades.NOMBRE_TABLA_CONTACTO, Utilidades.CAMPOS_TABLA_CONTACTO, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String _id = c.getString(0);
                String depenencia = c.getString(1);
                String nombre = c.getString(2);
                String telefono = c.getString(3);
                String extension = c.getString(4);
                contactos.add(new Contacto(_id, depenencia, nombre, telefono, extension));
            } while (c.moveToNext());
        }
        return contactos;
    }

    /**
     * Método que inserta un contacto en la base de datos.
     * @param campos Atributos del contacto.
     */
    public void insertarContacto(String... campos) {
        String insertar = "INSERT INTO ? (?,?,?,?) VALUES ( '?', '?', '?', '?' )";
        StringBuilder builder = new StringBuilder(insertar);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_CONTACTO);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_CONTACTO[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_CONTACTO[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_CONTACTO[3]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_CONTACTO[4]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[3]);
        Utilidades.mostrarMensajeConsola(builder.toString());
        ejecutarConsulta(builder.toString());
    }

    /**
     * Método que elimina un contacto de la base de datos.
     * @param idP ID del contacto.
     */
    public void elimarContacto(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_CONTACTO);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_CONTACTO[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }


    // Dependencia


    /**
     * Método que devuelve una sentencia para crear la tabla de dependencias.
     * @return Sentencia create para dependencias.
     */
    public static String crearTablaDependencia(){
        String crearTabla = "CREATE TABLE ? ( ? INTEGER PRIMARY KEY AUTOINCREMENT, ? TEXT, ? TEXT)";
        StringBuilder builder = new StringBuilder(crearTabla);
        builder.replace(builder.indexOf("?"), crearTabla.indexOf("?")+1,Utilidades.NOMBRE_TABLA_DEPENDENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_DEPENDENCIA[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_DEPENDENCIA[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_DEPENDENCIA[2]);
        return builder.toString();
    }

    /**
     * Método que recupera una lista con todas las dependencias de la base de datos.
     * @return Dependencias almacenadas en la db.
     */
    public ArrayList<Dependencia> getDependencias() {
        ArrayList<Dependencia> dependencias = new ArrayList<>();
        Cursor c = db.query(Utilidades.NOMBRE_TABLA_DEPENDENCIA, Utilidades.CAMPOS_TABLA_DEPENDENCIA, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String _id = c.getString(0);
                String imagen = c.getString(1);
                String nombre = c.getString(2);
                ArrayList<Contacto> contactos = getContactos();
                for (int i = 0; i < contactos.size(); i++) {
                    if (!contactos.get(i).getDependencia().equals(nombre)) {
                        contactos.remove(i);
                        i--;
                    }
                }
                dependencias.add(new Dependencia(_id, imagen, nombre, contactos));
            } while (c.moveToNext());
        }
        return dependencias;
    }

    /**
     * Método que inserta una dependencia en la base de datos.
     * @param campos Atributos de la dependencia.
     */
    public void insertarDependencia(String... campos) {
        String insertar = "INSERT INTO ? (?,?) VALUES ( '?', '?' )";
        StringBuilder builder = new StringBuilder(insertar);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_DEPENDENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_DEPENDENCIA[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_DEPENDENCIA[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[1]);
        Utilidades.mostrarMensajeConsola(builder.toString());
        ejecutarConsulta(builder.toString());
    }

    /**
     * Método que elimina una dependencia de la base de datos.
     * @param idP ID de la dependencia.
     */
    public void elimarDependencia(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_DEPENDENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_DEPENDENCIA[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }


    // Sugerencia


    /**
     * Método que devuelve una sentencia para crear la tabla de contactos.
     * @return Sentencia create para contactos.
     */
    public static String crearTablaSugerencia(){
        String crearTabla = "CREATE TABLE ? ( ? INTEGER PRIMARY KEY AUTOINCREMENT, ? TEXT, ? TEXT, ? TEXT, ? TEXT)";
        StringBuilder builder = new StringBuilder(crearTabla);
        builder.replace(builder.indexOf("?"), crearTabla.indexOf("?")+1,Utilidades.NOMBRE_TABLA_SUGERENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_SUGERENCIA[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_SUGERENCIA[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_SUGERENCIA[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_SUGERENCIA[3]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_SUGERENCIA[4]);
        return builder.toString();
    }

    /**
     * Método que recupera una lista con todas las sugerencias de la base de datos.
     * @return Sugerencias almacenadas en la db.
     */
    public ArrayList<Sugerencia> getSugerencias() {
        ArrayList<Sugerencia> sugerencias = new ArrayList<>();
        Cursor c = db.query(Utilidades.NOMBRE_TABLA_SUGERENCIA, Utilidades.CAMPOS_TABLA_SUGERENCIA, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String _id = c.getString(0);
                String asunto = c.getString(1);
                String detalle = c.getString(2);
                String imagen = c.getString(3);
                String estado = c.getString(4);
                sugerencias.add(new Sugerencia(_id, asunto, detalle, imagen, estado));
            } while (c.moveToNext());
        }
        return sugerencias;
    }

    /**
     * Método que inserta una sugerencia en la base de datos.
     * @param campos Atributos de la sugerencia.
     */
    public void insertarSugerencia(String... campos) {
        String insertar = "INSERT INTO ? (?,?,?,?) VALUES ( '?', '?', '?', '?' )";
        StringBuilder builder = new StringBuilder(insertar);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_SUGERENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_SUGERENCIA[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_SUGERENCIA[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_SUGERENCIA[3]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_SUGERENCIA[4]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[3]);
        Utilidades.mostrarMensajeConsola(builder.toString());
        ejecutarConsulta(builder.toString());
    }

    /**
     * Método que elimina una sugerencia de la base de datos.
     * @param idP ID de la sugerencia.
     */
    public void elimarSugerencia(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_SUGERENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_SUGERENCIA[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }

    // Consulta

    private void ejecutarConsulta(String consulta) {
        db.execSQL(consulta);
    }


}