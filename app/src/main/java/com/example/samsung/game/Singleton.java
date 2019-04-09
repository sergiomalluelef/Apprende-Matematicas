package com.example.samsung.game;

import android.content.Context;

/**
 * Created by Samsung on 06/09/2017.
 */

public class Singleton {
    Music music;
    public static Singleton instancia;
    Context context;

    public int etapas = 0;
    public int unidad = 1;
    public int unidad1 = 1;

    public Singleton(Context context) {
        this.context = context;
        music = new Music(context,"musica_fondo_dos");
    }
    public synchronized static Singleton getInstancia (Context context){
        if(instancia == null){
            instancia = new Singleton(context);
        }
        return instancia;
    }
}
