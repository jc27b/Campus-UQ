package com.uniquindio.android.electiva.campusuq.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Clase que implementa un ViewPager personalizado
 * que puede habilitar o deshabilitar el desplazamiento
 * por sus páginas.
 */
public class CustomViewPager extends android.support.v4.view.ViewPager {

    private boolean enabled;

    /**
     * Constructor del view pager personalizado.
     * @param context Contexto de la aplicación.
     * @param attrs Atributos del view pager.
     */
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    /**
     * Método llamado cuando se toca al view pager.
     * @param event Evento de movimiento.
     * @return Se realiza alguna acción o no.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return enabled ? super.onTouchEvent(event) : false;
    }

    /**
     * Método llamado cuando se intercepta un evento de toque.
     * @param event Evento de movimiento.
     * @return Se realiza alguna acción o no.
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return enabled ? super.onInterceptTouchEvent(event) : false;
    }

    /**
     * Método que habilita o deshabilita el desplazamiento por las páginas.
     * @param enabled Habilitación del desplazamiento.
     */
    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Método que dice si se puede cambiar de página.
     * @return Se puede cambiar de página o no.
     */
    public boolean isPagingEnabled() {
        return enabled;
    }

}
