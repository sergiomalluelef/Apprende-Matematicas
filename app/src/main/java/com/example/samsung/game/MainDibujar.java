package com.example.samsung.game;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.samsung.game.Fragments.Arrastrar;

import java.util.Random;

import static android.R.attr.duration;

public class MainDibujar extends AppCompatActivity implements View.OnTouchListener, Arrastrar.OnFragmentInteractionListener {

    GuiarPuntos dibujar;
    FrameLayout layout;
    Button btnvolverHome, btnVolver, btnOk;
    LinearLayout linearDibujo;
    View popupView;
    TextView txt1, txt2, txt3, txt4;
    private boolean inbackgr = false;

    Sonido sonido = new Sonido(this);
    MainMenus mainMenus = new MainMenus();

    private static final Random rgenerador = new Random();

    private static final Integer[] imagenesID = {R.drawable.personaje, R.drawable.facebook_icon, R.drawable.pizarra, R.drawable.usuario};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dibujar);
        Singleton.getInstancia(this);
        Singleton.getInstancia(getApplicationContext()).etapas = 0;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        dibujar = (GuiarPuntos) findViewById(R.id.dibujo);
        layout = (FrameLayout) findViewById(R.id.frameContenedor);

        btnVolver = (Button) findViewById(R.id.button2);
        btnvolverHome = (Button) findViewById(R.id.button3);

        btnVolver.setOnTouchListener(this);
        btnvolverHome.setOnTouchListener(this);

        btnOk = (Button) findViewById(R.id.btnokImg);
        btnOk.setOnTouchListener(this);

        linearDibujo = (LinearLayout) findViewById(R.id.LinearDibujo);

        /*Integer q = imagenesID[rgenerador.nextInt(imagenesID.length)];
        final ImageView iv = (ImageView) findViewById(R.id.imgaleatorias);

        View nextButton = findViewById(R.id.btnNextImg);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                int resource = imagenesID[rgenerador.nextInt(imagenesID.length)];
                iv.setImageResource(resource);
            }
        });*/


    }

    public void popupWindowClick() {
        Log.d("popWindows", "Entro");

        FrameLayout mainLayout = (FrameLayout) findViewById(R.id.frameContenedor);

        sonido.playSound("bien");
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.pop_resultado, null);

        int width = FrameLayout.LayoutParams.MATCH_PARENT;
        int height = FrameLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                capturarNivel();
                return true;
            }
        });
    }

    public void capturarNivel() {
        final GuiarPuntos puntos = new GuiarPuntos(this);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Arrastrar arrastrar = new Arrastrar();

        switch (Singleton.getInstancia(getApplicationContext()).etapas) {
            case 1:
                layout.removeAllViews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Singleton.getInstancia(getApplicationContext()).etapas = 2;
                        popupWindowClick();
                    }

                }, 100);

                break;
            case 2:
                layout.removeAllViews();
                Log.d("Etapas", String.valueOf(Singleton.getInstancia(this).etapas));
                transaction.add(R.id.frameContenedor, arrastrar);
                transaction.commit();
                break;
            case 3:
                layout.removeAllViews();
                Log.d("Etapas", String.valueOf(Singleton.getInstancia(this).etapas));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Singleton.getInstancia(getApplicationContext()).etapas = 4;
                        popupWindowClick();
                    }

                }, 100);

                break;
            case 4:
                layout.removeAllViews();
                Log.d("Etapas", String.valueOf(Singleton.getInstancia(this).etapas));
                transaction.add(R.id.frameContenedor, arrastrar);
                transaction.commit();
                break;
            case 5:
                layout.removeAllViews();
                Log.d("Etapas", String.valueOf(Singleton.getInstancia(this).etapas));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Singleton.getInstancia(getApplicationContext()).etapas = 6;
                        popupWindowClick();
                    }

                }, 100);

                break;
            case 6:
                layout.removeAllViews();
                Log.d("Etapas", String.valueOf(Singleton.getInstancia(this).etapas));
                transaction.add(R.id.frameContenedor, arrastrar);
                transaction.commit();
                break;
            case 7:
                layout.removeAllViews();
                Log.d("Etapas", String.valueOf(Singleton.getInstancia(this).etapas));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Singleton.getInstancia(getApplicationContext()).unidad1 = 2;
                        popupWindowClick();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent();
                                intent.setClass(getApplicationContext(), MainMenus.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.transicion_entrada, R.anim.transicion_salida);
                            }

                        }, 1000);

                    }

                }, 100);

                break;

        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (btnVolver == v) {
                sonido.playSound("click");
                Intent intent = new Intent();
                intent.setClass(this, MainMenus.class);
                startActivity(intent);
                overridePendingTransition(R.anim.transicion_entrada,R.anim.transicion_salida);
            }
            if (btnvolverHome == v) {
                sonido.playSound("click");
                Intent intent = new Intent();
                intent.setClass(this, MainGame.class);
                startActivity(intent);
                overridePendingTransition(R.anim.transicion_entrada,R.anim.transicion_salida);
            }
            if (btnOk == v) {

                capturarNivel();
            }


        }

        return false;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void mensajeAbajo(String mensaje) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG);

        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(MainDibujar.this, R.color.colorAccent));
        tv.setTextSize(18);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        snack.show();
    }

    public void transicion(){
        overridePendingTransition(R.anim.transicion_entrada,R.anim.transicion_salida);
    }
    @Override
    protected void onPause() {

        if(!inbackgr) Singleton.getInstancia(this).music.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        inbackgr = false;
        Singleton.getInstancia(this).music.playBg();
        super.onResume();
    }


}
