package com.uniquindio.android.electiva.campusuq.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.uniquindio.android.electiva.campusuq.R;
import com.uniquindio.android.electiva.campusuq.util.Utilidades;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;

/**
 * Fragmento que permite iniciar sesión,
 * para esto tiene los textos de las redes sociales, al
 * presionarlos se inicia sesión en ellas.
 */
public class LoginFragment extends Fragment {

    private TwitterLoginButton btnLoginTwitter;

    private OnLoginListener listener;

    /**
     * Constructor del fragmento vacio para instanciarlo
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo donde se configura la vista que utilizará el fragmento,
     * además se le pone titulo al diálogo y un listener a los
     * textos para iniciar sesión en las redes sociales.
     * @param inflater Objeto que permite cargar una vista al fragmento
     * @param savedInstanceState  Instancia guardada para restaurar los datos
     * @return Vista que usará el fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_login, container, false);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(Utilidades.TWITTER_KEY, Utilidades.TWITTER_SECRET);
        Fabric.with(super.getActivity(), new TwitterCore(authConfig), new TweetComposer());

        TextView txt1 = (TextView) vista.findViewById(R.id.txt_login_facebook);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogin(true);
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile", "user_friends"));
            }
        });

        btnLoginTwitter = (TwitterLoginButton) vista.findViewById(R.id.twitter_login_button);

        btnLoginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Utilidades.mostrarMensajeConsola(msg);
                listener.onLogin(false);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

        return vista;
    }

    /**
     * Método llamado cuando se crea la actividad.
     * Se encarga de inicializar la sesión de twitter.
     * @param savedInstanceState Instancia guardada para restaurar los datos.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TwitterSession session = Twitter.getSessionManager().getActiveSession();

        if( session != null ){
            Utilidades.mostrarMensajeConsola("Sesion iniciada por: "+session.getUserName());
        } else {
            Utilidades.mostrarMensajeConsola("No se inició la sesión");
        }
    }

    /**
     * Método encargado de llamar al onActivityResult del botón de login.
     * @param requestCode Código de solicitud.
     * @param resultCode Código de resultado.
     * @param data Intent con los datos.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        btnLoginTwitter.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Metodo llamado cuando se va a agregar el fragmento dentro
     * de la actividad, es utilizado en este caso para confirmar que
     * la actividad implemente una interfaz para pasar datos
     * a otro fragmento.
     * @param context Actividad del fragmento.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                listener = (OnLoginListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnLoginListener");
            }
        }

    }

    /**
     * Interfaz que sera implementada por la actividad para
     * compartir datos con otro fragmento.
     */
    public interface OnLoginListener {
        void onLogin(boolean loginWithFacebook);
    }

}
