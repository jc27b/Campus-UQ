package com.uniquindio.android.electiva.campusuq.vo;


import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Dependencia {

    private Drawable imagen;
    private String nombre;
    private ArrayList<Contacto> contactos;

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
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
