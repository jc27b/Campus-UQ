package com.uniquindio.android.electiva.campusuq.vo;


import java.util.ArrayList;

/**
 * Clase que representa una dependencia y
 * contiene toda su información, de modo
 * que esta se pueda obtener y reasignar.
 */
public class Dependencia {

    private String _id;
    private String imagen;
    private String nombre;
    private ArrayList<Contacto> contactos;

    /**
     * Constructor personalizado de la dependencia,
     * que inicializa sus atributos.
     * @param imagen Imágen de la dependencia.
     * @param nombre Nombre de la dependencia.
     * @param contactos Contactos de la dependencia.
     */
    public Dependencia(String imagen, String nombre, ArrayList<Contacto> contactos) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.contactos = contactos;
    }

    /**
     * Constructor personalizado de la dependencia,
     * que inicializa sus atributos.
     * @param _id Id de la dependencia;
     * @param imagen Imágen de la dependencia.
     * @param nombre Nombre de la dependencia.
     * @param contactos Contactos de la dependencia.
     */
    public Dependencia(String _id, String imagen, String nombre, ArrayList<Contacto> contactos) {
        this._id = _id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.contactos = contactos;
    }

    /**
     * Método para obtener la id de la dependencia.
     * @return Id de la dependencia.
     */
    public String get_id() {
        return _id;
    }

    /**
     * Método para asignarle id a la dependencia.
     * @param _id Id de la dependencia.
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Método para obtener la imágen de la dependencia.
     * @return Imágen de la dependencia.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Método para asignarle imágen a la dependencia.
     * @param imagen Imágen de la dependencia.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Método para obtener el nombre de la dependencia.
     * @return Nombre de la dependencia.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para asignarle nombre a la dependencia.
     * @param nombre Nombre de la dependencia.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener los contactos de la dependencia.
     * @return Contactos de la dependencia.
     */
    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    /**
     * Método para asignarle contactos a la dependencia.
     * @param contactos Contactos de la dependencia.
     */
    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

}
