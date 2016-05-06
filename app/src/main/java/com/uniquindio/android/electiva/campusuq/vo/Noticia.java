package com.uniquindio.android.electiva.campusuq.vo;

import android.graphics.Bitmap;

/**
 * Clase que representa una noticia y
 * contiene toda su información, de modo
 * que esta se pueda obtener y reasignar.
 */
public class Noticia {

    private Bitmap imagen;
    private String titulo;
    private String id;
    private String fecha;
    private String descripcion;

    /**
     * Constructor personalizado de la noticia,
     * que inicializa sus atributos.
     * @param imagen Imágen de la noticia.
     * @param titulo Título de la noticia.
     * @param id ID de la noticia.
     * @param fecha Fecha de la noticia.
     * @param descripcion Descripción de la noticia.
     */
    public Noticia(Bitmap imagen, String titulo, String id, String fecha, String descripcion) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Método para obtener la imágen de la noticia.
     * @return Imágen de la noticia.
     */
    public Bitmap getImagen() {
        return imagen;
    }

    /**
     * Método para asignarle imágen a la noticia.
     * @param imagen Imágen de la noticia.
     */
    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    /**
     * Método para obtener el título de la noticia.
     * @return Título de la noticia.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método para asignarle título a la noticia.
     * @param titulo Título de la noticia.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método para obtener la fecha de la noticia.
     * @return Fecha de la noticia.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Método para asignarle fecha a la noticia.
     * @param fecha Fecha de la noticia.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Método para obtener la ID de la noticia.
     * @return ID de la noticia.
     */
    public String getId() {
        return id;
    }

    /**
     * Método para asignarle ID a la noticia.
     * @param id ID de la noticia.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método para obtener la descripción de la noticia.
     * @return Descripción de la noticia.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método para asignarle descripción a la noticia.
     * @param descripcion Descripción de la noticia.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
