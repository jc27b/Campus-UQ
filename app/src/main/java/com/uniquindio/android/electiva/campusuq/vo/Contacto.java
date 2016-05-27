package com.uniquindio.android.electiva.campusuq.vo;

/**
 * Clase que representa un contacto y
 * contiene toda su información, de modo
 * que esta se pueda obtener y reasignar.
 */
public class Contacto {

    private String _id;
    private String dependencia;
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
     * Constructor personalizado del contacto,
     * que inicializa sus atributos.
     * @param _id Id del contacto.
     * @param dependencia Dependencia del contacto.
     * @param nombre Nombre del contacto.
     * @param telefono Teléfono del contacto.
     * @param extension Extensión del contacto.
     */
    public Contacto(String _id, String dependencia,String nombre, String telefono, String extension) {
        this._id = _id;
        this.dependencia = dependencia;
        this.nombre = nombre;
        this.telefono = telefono;
        this.extension = extension;
    }

    /**
     * Método para obtener la id del contacto.
     * @return Id del contacto.
     */
    public String get_id() {
        return _id;
    }

    /**
     * Método para asignarle id al contacto.
     * @param _id Id del contacto.
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Método para obtener la dependencia del contacto.
     * @return Dependencia del contacto.
     */
    public String getDependencia() {
        return dependencia;
    }

    /**
     * Método para asignarle dependencia al contacto.
     * @param dependencia Dependencia el contacto.
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
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
