package com.uniquindio.android.electiva.campusuq.util;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.NoticeFragment;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.util.ArrayList;

/**
 *  Clase que implementa el adaptador para mostrar una
 *  lista de noticias que serán cargadas en un arreglo.
 *  Se configura la vista en la que se pondrán los controles
 *  cargados en un holder para que luego a estos se le pongan
 *  los datos de cada una de las noticias.
 */
public class AdaptadorDeNoticia extends RecyclerView.Adapter<AdaptadorDeNoticia.NoticiaViewHolder> {

    private ArrayList<Noticia> noticias;
    private static OnClickAdaptadorDeNoticia listener;

    /**
     * Constructor del adaptador de noticias,
     * inicializa el arreglo de noticias y
     * el listener para los eventos.
     * @param noticias Arreglo que contiene las noticias.
     * @param noticeFragment Fragmento que implementa el listener.
     */
    public AdaptadorDeNoticia(ArrayList<Noticia> noticias, NoticeFragment noticeFragment) {
        this.noticias = noticias;
        listener = (OnClickAdaptadorDeNoticia) noticeFragment;
    }

    /**
     * Metodo que inicializa la vista cargando el resumen de noticia, la cual que sera
     * utilizada para cargar las noticias. Ademas se crea un view holder de noticia
     * utilizando como parametro la vista creada y se retorna.
     * @param parent Grupo de vistas en al que pertenecerá el holder que será retornado.
     * @param viewType Tipo de la vista que será utilizada.
     * @return Holder que estara contenido en cada uno de los items de la lista.
     */
    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_noticia, parent, false);
        NoticiaViewHolder noticiaVH = new NoticiaViewHolder(itemView);
        return noticiaVH;
    }

    /**
     * Metodo que actualizara la informacion en los controles de la vista,
     * de acuerdo a la posicion pasada por parametro, esto se hace
     * llamando al metodo binNoticia.
     * @param holder Holder que que contendra los controles para mostrar informacion.
     * @param position Posicion en la lista en la que se cargaran los datos.
     */
    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.binNoticia(noticia);
    }

    /**
     * Metodo que retorna el numero total de items que se quieren
     * mostrar en el RecyclerView.
     * @return El numero total de elementos que se mostraran.
     */
    @Override
    public int getItemCount() {
        return noticias.size();
    }

    /**
     * Clase que implementa el holder para almacenar todos los controles que son
     * cargados por el método findViewByID de cada uno de los ítems del control de
     * selección, ya que su carga es costosa. Ademas implementa el metodo
     * para asignarle valor a estos controles.
     */
    public static class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitulo;
        private ImageView imagen;

        /**
         * Metodo que utiliza la vista pasada por parametro para crea configurar
         * el holder, le asigna un listener a la vista, e inicializa los controles
         * que contendran valores especificos de acuerdo a la noticia.
         * @param itemView Resumen de noticia que sera una tarjeta donde se mostrara la informacion de la noticia.
         */
        public NoticiaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtTitulo = (TextView) itemView.findViewById(R.id.titulo);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_noticia);
        }

        /**
         * Metodo que asigna a los controles cargados en la
         * holder un valor de acuerdo a la noticia pasado
         * por parametro.
         * @param n Noticia a la que se le extraeran los datos.
         */
        public void binNoticia(Noticia n) {
            txtTitulo.setText(n.getTitulo());
            imagen.setImageBitmap(n.getImagen());
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
     * lista de noticias para que el adaptador le
     * informe cual item fue presionado.
     */
    public interface OnClickAdaptadorDeNoticia{
        public void onClickPosition(int pos);
    }

}
