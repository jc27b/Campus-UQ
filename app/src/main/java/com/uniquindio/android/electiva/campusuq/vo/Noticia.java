package com.uniquindio.android.electiva.campusuq.vo;

/**
 * Clase que representa una noticia y
 * contiene toda su información, de modo
 * que esta se pueda obtener y reasignar.
 */
public class Noticia {

    private String _id;
    private String imagen;
    private String titulo;
    private String fecha;
    private String descripcion;

    /**
     * Constructor personalizado de la noticia,
     * que inicializa sus atributos.
     * @param imagen Imágen de la noticia.
     * @param titulo Título de la noticia.
     * @param _id ID de la noticia.
     * @param fecha Fecha de la noticia.
     * @param descripcion Descripción de la noticia.
     */
    public Noticia(String _id, String imagen, String titulo, String fecha, String descripcion) {
        this._id = _id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Método para obtener la ID de la noticia.
     * @return ID de la noticia.
     */
    public String get_id() {
        return _id;
    }

    /**
     * Método para asignarle ID a la noticia.
     * @param _id ID de la noticia.
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Método para obtener la imágen de la noticia.
     * @return Imágen de la noticia.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Método para asignarle imágen a la noticia.
     * @param imagen Imágen de la noticia.
     */
    public void setImagen(String imagen) {
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
