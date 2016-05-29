package com.uniquindio.android.electiva.campusuq.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.CRUD;
import com.uniquindio.android.electiva.campusuq.util.CRUDSQL;
import com.uniquindio.android.electiva.campusuq.util.Utilidades;
import com.uniquindio.android.electiva.campusuq.vo.Sugerencia;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Fragmento que le permitirá al usuario enviar sugerencias
 * con asunto y detalle, así como con una imagen adjunta
 * escogida por el usuario.
 */
public class SuggestionFragment extends Fragment {

    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private final int SELECT_PHOTO = 1;

    private CRUDSQL crudsql;
    private SuggestionFragment suggestionFragment;

    /**
     * Constructor por defecto del fragmento
     * el cual es necesario que sea publico
     * y vacio para instanciarlo
     */
    public SuggestionFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que permite crear una instacia del fragmento.
     * @return Instancia del fragmento que será utilizada en el view pager según el adaptador.
     */
    public static SuggestionFragment newInstance() {

        SuggestionFragment fragment = new SuggestionFragment();

        return fragment;

    }

    /**
     * Metodo llamado cuando se crea el fragmento,
     * llama a otro callback de la superclase.
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        suggestionFragment = this;
    }

    /**
     * Método que crea la vista que utilizará el fragmento,
     * también se encarga de asignar listeners a los botones,
     * para que se puedan cargar imágenes y enviarlas
     * junto a la sugerencia.
     * @param inflater Objeto que permitirá inflar la vista para el fragmento
     * @param container Contenedor según la jerarquía de vistas de la actividad
     * @param savedInstanceState Instancia guardada para restaurar los datos
     * @return Vista que usará el fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_suggestion, container, false);

        Button pickImage = (Button) vista.findViewById(R.id.btn_pick);
        pickImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                try {
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                } catch (SecurityException se) {

                }
            }

        });

        return vista;
    }

    /**
     * Método ejecutado cuando se crea la actividad.
     * Se encarga de ponerle un listener de eventos
     * al botón de enviar sugerencia.
     * @param savedInstanceState Instancia guardada para restaurar los datos.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        crudsql = new CRUDSQL(getActivity(), 1);

        Button sendSuggestion = (Button) getView().findViewById(R.id.btn_send);
        sendSuggestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String asunto = ((TextView) getView().findViewById(R.id.edit_text_asunto)).getText().toString();
                String detalle = ((TextView) getView().findViewById(R.id.edit_text_detalle)).getText().toString();

                ImageView imageView = (ImageView) getView().findViewById(R.id.imagen_sugerencia);
                Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                String imageString = Base64.encodeToString(byteArray, Base64.NO_WRAP);

                String estado = "Revisando";
                Sugerencia sugerencia = new Sugerencia(asunto, detalle, imageString, estado);

                HiloSecundarioSugerencia hiloSecundario = new HiloSecundarioSugerencia(suggestionFragment.getContext());
                hiloSecundario.setSugerencia(sugerencia);
                hiloSecundario.execute(Utilidades.AGREGAR_SUGERENCIA);

                Toast.makeText(getContext(), getResources().getString(R.string.sugerencia_enviada), Toast.LENGTH_SHORT).show();
            }

        });

    }

    /**
     * Método que permite obtener la imagen seleccionada
     * por el usuario una vez que se ha obtenido un
     * resultado de la actividad llamada, ésta es
     * cargada en el image view.
     * @param requestCode Código de solicitud.
     * @param resultCode Código de resultado.
     * @param imageReturnedIntent Intent con la imagen obtenida.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        ImageView imageView = (ImageView) getView().findViewById(R.id.imagen_sugerencia);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }

    }

    /**
     * Clase que implementa un hilo secundario para realizar
     * operaciones con con servicios.
     */
    public class HiloSecundarioSugerencia extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progress;
        private Context context;
        private Sugerencia sugerencia;

        /**
         * Constructor del hilo secundario, que
         * inicializa el contexto y la sugerencia.
         * @param context Contexto de la aplicación.
         */
        public HiloSecundarioSugerencia(Context context) {
            this.context = context;
            sugerencia = null;
        }

        /**
         * Metodo ejecutado antes de que
         * se ejecute el hilo, muestra un
         * mensaje informativo.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(context, context.getString(R.string.cargando_sugerencias), context.getString(R.string.espere), true);
        }

        /**
         * Metodo que se ejecuta en el hilo secundario,
         * el cual permite agregar sugerencias.
         * @param params Operación a realizar.
         * @return Operación realizada.
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            if (params[0] == Utilidades.AGREGAR_SUGERENCIA) {
                String sugerenciaJSON = Utilidades.convertirSugerenciaAJSON(sugerencia);
                sugerencia = CRUD.agregarSugerenciaAlServicio(sugerenciaJSON);
            }

            return params[0];
        }

        /**
         * Metodo ejecutado después de que
         * el hilo finalizó su ejecución.
         * Pone el los controles gráficos
         * la información extraída del servicio.
         * @param integer Operación a realizar.
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (integer == Utilidades.AGREGAR_SUGERENCIA) {
                if (sugerencia != null) {
                    crudsql.insertarSugerencia(sugerencia.getAsunto(), sugerencia.getDetalle(), sugerencia.getImagen(), sugerencia.getEstado());
                    sugerencia = null;
                }
                else {
                    Utilidades.mostrarMensaje(Utilidades.NO_SE_AGREGO_LA_SUGERENCIA, getContext());
                }

            }

            progress.dismiss();
        }

        /**
         * Permite obtener la sugerencia del hilo.
         * @return Sugerencia del hilo.
         */
        public Sugerencia getSugerencia() {
            return sugerencia;
        }

        /**
         * Permite establecer la sugerencia del hilo.
         * @param sugerencia Sugerencia a establecer.
         */
        public void setSugerencia(Sugerencia sugerencia) {
            this.sugerencia = sugerencia;
        }

    }

}
