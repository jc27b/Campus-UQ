package com.uniquindio.android.electiva.campusuq.vo;

import android.graphics.Bitmap;

public class Noticia {

    private Bitmap imagen;
    private String titulo;
    private String id;
    private String fecha;
    private String descripcion;

    public Noticia(Bitmap imagen, String titulo, String id, String fecha, String descripcion) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
