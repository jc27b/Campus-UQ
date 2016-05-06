package com.uniquindio.android.electiva.campusuq.vo;

/**
 * Clase que representa un contacto y
 * contiene toda su información, de modo
 * que esta se pueda obtener y reasignar.
 */
public class Contacto {

    private String nombre;
    private String telefono;
    private String extension;

    /**
     * Constructor personalizado del contacto,
     * que inicializa sus atributos.
     * @param nombre Nombre del contacto.
     * @param telefono Teléfono del contacto.
     * @param extension Extensión del contacto.
     */
    public Contacto(String nombre, String telefono, String extension) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.extension = extension;
    }

    /**
     * Método para obtener el nombre del contacto.
     * @return Nombre del contacto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para asignarle nombre al contacto.
     * @param nombre Nombre del contacto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener la extensión del contacto.
     * @return Extensión del contacto.
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Método para asignarle extensión al contacto.
     * @param extension Extensión del contacto.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Método para obtener el teléfono del contacto.
     * @return Teléfono del contacto.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método para asignarle teléfono al contacto.
     * @param telefono Teléfono del contacto.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
