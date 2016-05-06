package com.uniquindio.android.electiva.campusuq.vo;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class Dependencia {

    private Bitmap imagen;
    private String nombre;
    private ArrayList<Contacto> contactos;

    public Dependencia(Bitmap imagen, String nombre, ArrayList<Contacto> contactos) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.contactos = contactos;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

}
