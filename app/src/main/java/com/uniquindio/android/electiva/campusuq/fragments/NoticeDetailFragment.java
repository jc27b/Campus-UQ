package com.uniquindio.android.electiva.campusuq.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.Utilidades;
import com.uniquindio.android.electiva.campusuq.vo.Noticia;

import java.net.MalformedURLException;
import java.net.URL;

import io.fabric.sdk.android.Fabric;

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

    private ShareDialog shareDialog;

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

        View view = inflater.inflate(R.layout.fragment_notice_detail, container, false);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(Utilidades.TWITTER_KEY, Utilidades.TWITTER_SECRET);
        Fabric.with(super.getActivity(), new TwitterCore(authConfig), new TweetComposer());

        return view;
    }

    /**
     * Método llamado cuando se crea la actividad.
     * Se encarga de inicializar el share dialog y la sesión de twitter.
     * @param savedInstanceState Instancia guardada para restaurar los datos.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        shareDialog = new ShareDialog(getActivity());

        TwitterSession session = Twitter.getSessionManager().getActiveSession();

        if( session != null ){
            Utilidades.mostrarMensajeConsola("Sesion iniciada por: "+session.getUserName());
        } else {
            Utilidades.mostrarMensajeConsola("No se inició la sesión");
        }

    }

    /**
     * Metodo llamado desde la actividad principal
     * para poner a los controles del fragmento los datos de
     * la noticia que fue seleccionada.
     * @param noticia Noticia seleccionada.
     */
    public void mostrarNoticia (final Noticia noticia) {
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
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setContentTitle(noticia.getTitulo())
                                .setContentUrl(Uri.parse("https://www.uniquindio.edu.co/"))
                                .setContentDescription(noticia.getDescripcion())
                                .build();
                        shareDialog.show(content);
                    }
                }
            }
        });
        btnCompartirTwitter = (android.support.design.widget.FloatingActionButton) getView().findViewById(R.id.btn_compartir_twitter);
        btnCompartirTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    URL url = new URL("https://www.uniquindio.edu.co/");
                    TweetComposer.Builder builder = new
                            TweetComposer.Builder(getContext())
                            .text(noticia.getTitulo())
                            .url(url);
                    builder.show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
