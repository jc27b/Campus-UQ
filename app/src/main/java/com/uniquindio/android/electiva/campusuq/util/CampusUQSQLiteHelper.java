package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan Camilo on 26/05/2016.
 */
public class CampusUQSQLiteHelper extends SQLiteOpenHelper {

    public CampusUQSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRUDSQL.crearTablaContacto());
        db.execSQL(CRUDSQL.crearTablaDependencia());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
