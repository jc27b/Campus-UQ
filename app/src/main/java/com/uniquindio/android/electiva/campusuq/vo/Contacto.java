package com.uniquindio.android.electiva.campusuq.vo;


public class Contacto {

    private String nombre;
    private String telefono;
    private String extension;

    public Contacto(String nombre, String telefono, String extension) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.extension = extension;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
