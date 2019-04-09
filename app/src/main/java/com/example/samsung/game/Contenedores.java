package com.example.samsung.game;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by Samsung on 08/05/2017.
 */
public class Contenedores extends FrameLayout {

    private Context context;
    private int width = 0;
    private int height = 0;
    private int tamañoOriginalWidth = 0;
    private int tamañoOriginalHeigth = 0;


    public Contenedores(Context context) {
        super(context);

        this.context = context;
        this.setLayoutParams(
                new LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                )
        );
    }

    public Contenedores(Context context, int color, int width, int height) {
        super(context);
        this.context = context;
        this.tamañoOriginalWidth = width; //ancho
        this.tamañoOriginalHeigth = height; //alto
        //this.width = this.tamañoOriginalWidth;
        //this.height = this.tamañoOriginalHeigth;

        this.setBackgroundColor(color);

        this.setLayoutParams(
                new LayoutParams(
                        this.tamañoOriginalWidth,
                        this.tamañoOriginalHeigth
                )
        );
    }


}
