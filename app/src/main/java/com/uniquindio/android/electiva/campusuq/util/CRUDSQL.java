package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.uniquindio.android.electiva.campusuq.vo.Contacto;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

/**
 * Created by JuanCamilo on 20/05/2016.
 */
public class CRUDSQL {

    private CampusUQSQLiteHelper usdbh;
    private SQLiteDatabase db;

    public CRUDSQL(Context context, int version) {
        usdbh = new CampusUQSQLiteHelper(context, Utilidades.NOMBRE_BD , null, version);
        db = usdbh.getWritableDatabase();
    }

    // Contacto

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

    public void elimarContacto(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_CONTACTO);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_CONTACTO[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }

    // Dependencia

    public static String crearTablaDependencia(){
        String crearTabla = "CREATE TABLE ? ( ? INTEGER PRIMARY KEY AUTOINCREMENT, ? TEXT, ? TEXT)";
        StringBuilder builder = new StringBuilder(crearTabla);
        builder.replace(builder.indexOf("?"), crearTabla.indexOf("?")+1,Utilidades.NOMBRE_TABLA_DEPENDENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_DEPENDENCIA[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_DEPENDENCIA[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_DEPENDENCIA[2]);
        return builder.toString();
    }

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

    public void elimarDependencia(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_DEPENDENCIA);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_DEPENDENCIA[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }

    private void ejecutarConsulta(String consulta) {
        db.execSQL(consulta);
    }





}