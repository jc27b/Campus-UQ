package com.uniquindio.android.electiva.campusuq.util;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.fragments.DirectoryFragment;
import com.uniquindio.android.electiva.campusuq.vo.Dependencia;

import java.util.ArrayList;

/**
 *  Clase que implementa el adaptador para mostrar una
 *  lista de dependencias que serán cargadas en un arreglo.
 *  Se configura la vista en la que se pondrán los controles
 *  cargados en un holder para que luego a estos se le pongan
 *  los datos de cada una de las dependencias.
 */
public class AdaptadorDeDependencia extends RecyclerView.Adapter<AdaptadorDeDependencia.DependenciaViewHolder> {

    private ArrayList<Dependencia> directorio;
    private static OnClickAdaptadorDeDirectorio listener;

    /**
     * Constructor del adaptador de dependencias,
     * inicializa el arreglo de dependencias y
     * el listener para los eventos.
     * @param directorio Arreglo que contiene las dependencias.
     * @param directoryFragment Fragmento que implementa el listener.
     */
    public AdaptadorDeDependencia(ArrayList<Dependencia> directorio, DirectoryFragment directoryFragment) {
        this.directorio = directorio;
        listener = (OnClickAdaptadorDeDirectorio) directoryFragment;
    }

    /**
     * Metodo que inicializa la vista cargando el resumen de dependencia, la cual que sera
     * utilizada para cargar las dependencias. Ademas se crea un view holder de dependencia
     * utilizando como parametro la vista creada y se retorna.
     * @param parent Grupo de vistas en al que pertenecerá el holder que será retornado.
     * @param viewType Tipo de la vista que será utilizada.
     * @return Holder que estara contenido en cada uno de los items de la lista.
     */
    @Override
    public DependenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_dependencia, parent, false);
        DependenciaViewHolder noticiaVH = new DependenciaViewHolder(itemView);
        return noticiaVH;
    }

    /**
     * Metodo que actualizara la informacion en los controles de la vista,
     * de acuerdo a la posicion pasada por parametro, esto se hace
     * llamando al metodo binDependencia.
     * @param holder Holder que que contendra los controles para mostrar informacion.
     * @param position Posicion en la lista en la que se cargaran los datos.
     */
    @Override
    public void onBindViewHolder(DependenciaViewHolder holder, int position) {
        Dependencia dependencia = directorio.get(position);
        holder.binDependencia(dependencia);
    }

    /**
     * Metodo que retorna el numero total de items que se quieren
     * mostrar en el RecyclerView.
     * @return El numero total de elementos que se mostraran.
     */
    @Override
    public int getItemCount() {
        return directorio.size();
    }

    /**
     * Clase que implementa el holder para almacenar todos los controles que son
     * cargados por el método findViewByID de cada uno de los ítems del control de
     * selección, ya que su carga es costosa. Ademas implementa el metodo
     * para asignarle valor a estos controles.
     */
    public static class DependenciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNombre;
        private ImageView imagen;

        /**
         * Metodo que utiliza la vista pasada por parametro para crea configurar
         * el holder, le asigna un listener a la vista, e inicializa los controles
         * que contendran valores especificos de acuerdo a la dependencia.
         * @param itemView Resumen de dependencia que sera una tarjeta donde se mostrara la informacion de la dependencia.
         */
        public DependenciaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_dependencia);
        }

        /**
         * Metodo que asigna a los controles cargados en la
         * holder un valor de acuerdo a la dependencia pasado
         * por parametro.
         * @param d Dependencia a la que se le extraeran los datos.
         */
        public void binDependencia(Dependencia d) {
            txtNombre.setText(d.getNombre());
            String imageString = d.getImagen();
            byte[] byteArray = Base64.decode(imageString, Base64.NO_WRAP);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imagen.setImageBitmap(bmp);
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
     * lista de dependencias para que el adaptador le
     * informe cual item fue presionado.
     */
    public interface OnClickAdaptadorDeDirectorio {
        public void onClickPosition(int pos);
    }

    /**
     * Método utilizado para que la lista de
     * dependencias sea actualizada en el adaptador.
     * @param dependencias Listado de dependencias.
     */
    public void intercambiar(ArrayList<Dependencia> dependencias){
        this.directorio.clear();
        this.directorio.addAll(dependencias);
        notifyDataSetChanged();
    }


}
