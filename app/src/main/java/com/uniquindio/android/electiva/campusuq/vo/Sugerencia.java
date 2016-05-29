package com.uniquindio.android.electiva.campusuq.vo;

/**
 * Clase que representa una sugerencia y
 * contiene toda su información, de modo
 * que esta se pueda obtener y reasignar.
 */
public class Sugerencia {

    private String _id;
    private String asunto;
    private String detalle;
    private String imagen;
    private String estado;

    /**
     * Constructor personalizado de la sugerencia,
     * que inicializa sus atributos.
     * @param asunto Asunto de la sugerencia.
     * @param detalle Detalle de la sugerencia.
     * @param imagen Imágen de la sugerencia.
     * @param estado Estado de la sugerencia.
     */
    public Sugerencia(String asunto, String detalle, String imagen, String estado) {
        this.asunto = asunto;
        this.detalle = detalle;
        this.imagen = imagen;
        this.estado = estado;
    }

    /**
     * Constructor personalizado de la sugerencia,
     * que inicializa sus atributos.
     * @param _id Id de la sugerencia.
     * @param asunto Asunto de la sugerencia.
     * @param detalle Detalle de la sugerencia.
     * @param imagen Imágen de la sugerencia.
     * @param estado Estado de la sugerencia.
     */
    public Sugerencia(String _id, String asunto, String detalle, String imagen, String estado) {
        this._id = _id;
        this.asunto = asunto;
        this.detalle = detalle;
        this.imagen = imagen;
        this.estado = estado;
    }

    /**
     * Método para obtener la ID de la sugerencia.
     * @return ID de la sugerencia.
     */
    public String get_id() {
        return _id;
    }

    /**
     * Método para asignarle ID a la sugerencia.
     * @param _id ID de la sugerencia.
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Método para obtener el asunto de la sugerencia.
     * @return Asunto de la sugerencia.
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Método para asignarle asunto a la sugerencia.
     * @param asunto Asunto de la sugerencia.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * Método para obtener el detalle de la sugerencia.
     * @return Detalle de la sugerencia.
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Método para asignarle detalle a la sugerencia.
     * @param detalle Detalle de la sugerencia.
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * Método para obtener la imágen de la sugerencia.
     * @return Imágen de la sugerencia.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Método para asignarle imágen a la sugerencia.
     * @param imagen Imágen de la sugerencia.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Método para obtener el estado de la sugerencia.
     * @return Estado de la sugerencia.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método para asignarle estado a la sugerencia.
     * @param estado Estado de la sugerencia.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
