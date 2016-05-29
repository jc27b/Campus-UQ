package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que facilita la creación y modificación de las bases de datos.
 */
public class CampusUQSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Constructor del ayudante.
     * @param context contexto con el que se relacionará el helper.
     * @param name nombre de la base de datos donde se almacenará la información.
     * @param factory permite usar el estándar <<SQLiteCursor>> para obtener los resultados de la base de datos.
     * @param version entero que representa la versión de la base de datos.
     */
    public CampusUQSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Este método es llamado automáticamente cuando se crea una instancia de la clase
     * <<SQLiteOpenHelper>>. En su interior se realiza la creación de las tablas y registros.
     * @param db referencia de la clase <<SQLiteDataBase>>.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRUDSQL.crearTablaContacto());
        db.execSQL(CRUDSQL.crearTablaDependencia());
        db.execSQL(CRUDSQL.crearTablaSugerencia());
    }

    /**
     * Es ejecutado si se identificó que el usuario tiene una versión antigua de la base de
     * datos. En su interior se crean las instrucciones para modificar el esquema de la base
     * de datos, como por ejemplo: eliminar el esquema y recrearlo, agregar una nueva
     * tabla, añadir una nueva columna, etc.
     * @param db representa la base de datos.
     * @param oldVersion un entero que indica la versión antigua de la base de datos.
     * @param newVersion entero que se refiere a la nueva versión de la base de datos.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
