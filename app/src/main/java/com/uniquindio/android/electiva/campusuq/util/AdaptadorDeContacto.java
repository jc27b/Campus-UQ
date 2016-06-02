package com.uniquindio.android.electiva.campusuq.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryDetailFragment;
import com.uniquindio.android.electiva.campusuq.vo.Contacto;

import java.util.ArrayList;

/**
 *  Clase que implementa el adaptador para mostrar una
 *  lista de contactos que serán cargados en un arreglo.
 *  Se configura la vista en la que se pondrán los controles
 *  cargados en un holder para que luego a estos se le pongan
 *  los datos de cada uno de los contactos.
 */
public class AdaptadorDeContacto extends RecyclerView.Adapter<AdaptadorDeContacto.ContactoViewHolder> {

    private ArrayList<Contacto> dependencia;
    private static OnClickAdaptadorDeContacto listener;

    /**
     * Constructor del adaptador de contactos,
     * inicializa el arreglo de contactos y
     * el listener para los eventos.
     * @param dependencia Dependencia que contiene los contactos.
     * @param directoryDetailFragment Fragmento que implementa el listener.
     */
    public AdaptadorDeContacto(ArrayList<Contacto> dependencia, DirectoryDetailFragment directoryDetailFragment) {
        this.dependencia = dependencia;
        listener = (OnClickAdaptadorDeContacto) directoryDetailFragment;
    }

    /**
     * Metodo que inicializa la vista cargando el resumen de contacto, la cual que sera
     * utilizada para cargar los contactos. Ademas se crea un view holder de contacto
     * utilizando como parametro la vista creada y se retorna.
     * @param parent Grupo de vistas en al que pertenecerá el holder que será retornado.
     * @param viewType Tipo de la vista que será utilizada.
     * @return Holder que estara contenido en cada uno de los items de la lista.
     */
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_contacto, parent, false);
        ContactoViewHolder noticiaVH = new ContactoViewHolder(itemView);
        return noticiaVH;
    }

    /**
     * Metodo que actualizara la informacion en los controles de la vista,
     * de acuerdo a la posicion pasada por parametro, esto se hace
     * llamando al metodo binContacto.
     * @param holder Holder que que contendra los controles para mostrar informacion.
     * @param position Posicion en la lista en la que se cargaran los datos.
     */
    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        Contacto contacto = dependencia.get(position);
        holder.binContacto(contacto);
    }

    /**
     * Metodo que retorna el numero total de items que se quieren
     * mostrar en el RecyclerView.
     * @return El numero total de elementos que se mostraran.
     */
    @Override
    public int getItemCount() {
        return dependencia.size();
    }

    /**
     * Clase que implementa el holder para almacenar todos los controles que son
     * cargados por el método findViewByID de cada uno de los ítems del control de
     * selección, ya que su carga es costosa. Ademas implementa el metodo
     * para asignarle valor a estos controles.
     */
    public static class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNombre;
        private TextView txtTelefono;
        private TextView txtExtension;

        /**
         * Metodo que utiliza la vista pasada por parametro para crea configurar
         * el holder, le asigna un listener a la vista, e inicializa los controles
         * que contendran valores especificos de acuerdo al contacto.
         * @param itemView Resumen de contacto que sera una tarjeta donde se mostrara la informacion del contacto.
         */
        public ContactoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre_de_contacto);
            txtTelefono = (TextView) itemView.findViewById(R.id.telefono_de_contacto);
            txtExtension = (TextView) itemView.findViewById(R.id.extension_de_contacto);
        }

        /**
         * Metodo que asigna a los controles cargados en la
         * holder un valor de acuerdo al contacto pasado
         * por parametro.
         * @param c Contacto al que se le extraeran los datos.
         */
        public void binContacto(Contacto c) {
            txtNombre.setText(c.getNombre());
            txtTelefono.setText(c.getTelefono());
            txtExtension.setText(c.getExtension());
        }

        /**
         * Metodo llamado cuando se da click en un elemento de la lista.
         * Llama el metodo del fragmento para comunicar la
         * accion a la actividad que lo contiene.
         * @param v La vista en la que se hizo click.
         */
        @Override
        public void onClick(View v) {
            listener.onClickPosition(getAdapterPosition());
        }
    }

    /**
     * Interfaz que deberá implementar el fragmento de la
     * lista de contactos para que el adaptador le
     * informe cual item fue presionado.
     */
    public interface OnClickAdaptadorDeContacto {
        public void onClickPosition(int pos);
    }



}
