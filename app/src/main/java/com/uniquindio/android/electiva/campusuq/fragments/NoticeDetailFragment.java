package com.uniquindio.android.electiva.campusuq.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

/**
 * Fragmento que se encargará de mostrar el detalle de
 * una noticia cuando esta es seleccionada en otro fragmento.
 */
public class NoticeDetailFragment extends Fragment {

    private ImageView imagen;
    private TextView titulo;
    private TextView id;
    private TextView fecha;
    private TextView detalle;

    private Noticia noticia;
    private android.support.design.widget.FloatingActionButton btnCompartirFacebook;
    private android.support.design.widget.FloatingActionButton btnCompartirTwitter;

    /**
     * Constructor del fragmento que mostrará
     * el detalle de una noticia.
     */
    public NoticeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Método llamado cuando se crea el fragmento.
     * Llama al mismo de la superclase.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Metodo que se encarga de crear la vista que utilizara
     * el fragmento, por medio del metodo inflate.
     * @param inflater Encargado de poner la vista en el fragmento.
     * @param container Jerarquia de vistas de la actividad.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     * @return Vista creada para el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice_detail, container, false);
    }

    /**
     * Metodo llamado desde la actividad principal
     * para poner a los controles del fragmento los datos de
     * la noticia que fue seleccionada.
     * @param noticia Noticia seleccionada.
     */
    public void mostrarNoticia (Noticia noticia) {
        this.noticia = noticia;
        imagen = (ImageView) getView().findViewById(R.id.imagen_detalle);

        String imageString = noticia.getImagen();
        byte[] byteArray = Base64.decode(imageString, Base64.NO_WRAP);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imagen.setImageBitmap(bmp);

        titulo = (TextView) getView().findViewById(R.id.titulo_de_detalle_noticia);
        titulo.setText(noticia.getTitulo());
        id = (TextView) getView().findViewById(R.id.id_de_detalle_noticia);
        id.setText(noticia.get_id());
        fecha = (TextView) getView().findViewById(R.id.fecha_de_detalle_noticia);
        fecha.setText(noticia.getFecha());
        detalle = (TextView) getView().findViewById(R.id.descripcion_de_detalle_noticia);
        detalle.setText(noticia.getDescripcion());
        btnCompartirFacebook = (android.support.design.widget.FloatingActionButton) getView().findViewById(R.id.btn_compartir_facebook);
        btnCompartirFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getResources().getString(R.string.compartida_facebook), Toast.LENGTH_SHORT).show();
            }
        });
        btnCompartirTwitter = (android.support.design.widget.FloatingActionButton) getView().findViewById(R.id.btn_compartir_twitter);
        btnCompartirTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getResources().getString(R.string.compartida_twitter), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
