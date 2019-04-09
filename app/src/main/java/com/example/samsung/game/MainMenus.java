package com.example.samsung.game;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.samsung.game.Fragments.SubMenusFragment;
import com.example.samsung.game.Fragments.Vista_menus;

import java.util.Timer;
import java.util.TimerTask;

public class MainMenus extends AppCompatActivity implements View.OnTouchListener, SubMenusFragment.OnFragmentInteractionListener, Vista_menus.OnFragmentInteractionListener {

    ImageView nube, unidadUno, animacion;
    Button buttonVolver, btnMenus;
    RelativeLayout layout;
    FrameLayout scena;
    CardView view;
    Sonido sonido = new Sonido(this);

    ImageView imgunidaddos, imgunidadtres, imgunidadcuatro;

    int i = 0;


    private boolean bandera = false;
    private boolean inbackgr = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menus);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Singleton.getInstancia(this);
        layout = (RelativeLayout) findViewById(R.id.frameMenus);
        view = (CardView) findViewById(R.id.CardViewMenus);
        scena = (FrameLayout) findViewById(R.id.scene);
        scena.setOnTouchListener(this);

        nube = (ImageView) findViewById(R.id.imagennube);
        buttonVolver = (Button) findViewById(R.id.btnVolver);
        buttonVolver.setOnTouchListener(this);

        animacion = (ImageView) findViewById(R.id.imageView8);
        animacion.setOnTouchListener(this);

        btnMenus = (Button) findViewById(R.id.btnMenus);
        btnMenus.setOnTouchListener(this);


        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 6.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(100000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = nube.getWidth();
                final float translationX = width * progress;
                nube.setTranslationX(translationX - width);

            }
        });
        animator.start();

        unidadUno = (ImageView) findViewById(R.id.imageView3);
        unidadUno.setOnTouchListener(this);
        unidadUno.setZ(100);


        if (Singleton.getInstancia(this).unidad == 1) {
            imgunidaddos = (ImageView) findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) findViewById(R.id.imageView10);

            imgunidaddos.setOnTouchListener(this);
            imgunidadtres.setOnTouchListener(this);
            imgunidadcuatro.setOnTouchListener(this);
        }
        if (Singleton.getInstancia(this).unidad == 2) {
            imgunidaddos = (ImageView) findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) findViewById(R.id.imageView10);

            imgunidaddos.setOnTouchListener(this);
            imgunidadtres.setOnTouchListener(this);
            imgunidadcuatro.setOnTouchListener(this);

            imgunidaddos.setVisibility(View.INVISIBLE);
        }
        if (Singleton.getInstancia(this).unidad == 3) {
            imgunidaddos = (ImageView) findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) findViewById(R.id.imageView10);

            imgunidaddos.setOnTouchListener(this);
            imgunidadtres.setOnTouchListener(this);
            imgunidadcuatro.setOnTouchListener(this);

            imgunidaddos.setVisibility(View.INVISIBLE);
            imgunidadtres.setVisibility(View.INVISIBLE);
        }
        if (Singleton.getInstancia(this).unidad == 4) {
            imgunidaddos = (ImageView) findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) findViewById(R.id.imageView10);

            imgunidaddos.setOnTouchListener(this);
            imgunidadtres.setOnTouchListener(this);
            imgunidadcuatro.setOnTouchListener(this);

            imgunidaddos.setVisibility(View.INVISIBLE);
            imgunidadtres.setVisibility(View.INVISIBLE);
            imgunidadcuatro.setVisibility(View.INVISIBLE);
        }
        cambiarVista();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        SubMenusFragment fragment = new SubMenusFragment();
        Vista_menus menus = new Vista_menus();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (buttonVolver == v) {
                sonido.playSound("click");
                Intent intent = new Intent();
                intent.setClass(this, MainGame.class);
                startActivity(intent);
                overridePendingTransition(R.anim.transicion_entrada, R.anim.transicion_salida);
            }
            if (unidadUno == v) {
                sonido.playSound("click");
                layout.removeAllViews();

                transaction.add(R.id.frameMenus, fragment);

                transaction.commit();
            }
            if (imgunidaddos == v) {
                sonido.playSound("error");
                clickIf(imgunidaddos);

            }
            if (imgunidadtres == v) {
                sonido.playSound("error");
                clickIf(imgunidadtres);

            }
            if (imgunidadcuatro == v) {
                sonido.playSound("error");
                clickIf(imgunidadcuatro);
            }

            if (btnMenus == v) {
                sonido.playSound("click");
                layout.removeAllViews();

                transaction.add(R.id.frameMenus, menus);

                transaction.commit();

            }
            if (animacion == v) {

                Singleton.getInstancia(this).music.downVolume();
                if (i == 0) {
                    sonido.playSound("bienvenido");
                    i++;
                } else if (i == 1) {
                    sonido.playSound("hola_como_estas");
                    i++;
                } else {
                    sonido.playSound("vamos_a_aprender_matematicas");
                }


            }


        }


        return false;
    }

    public void iraNivel(View v) {

        Intent intent = new Intent();
        intent.setClass(this, MainDibujar.class);
        startActivity(intent);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (bandera) return;
        this.bandera = true;

    }

    public void cambiarVista() {

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SubMenusFragment fragment = new SubMenusFragment();


        if (Singleton.getInstancia(this).unidad1 == 2) {

            layout.removeAllViews();
            transaction.add(R.id.frameMenus, fragment);
            transaction.commit();
            Log.d("niveles", String.valueOf(Singleton.getInstancia(this).unidad1));

        }
    }

    @Override
    protected void onPause() {

        if (!inbackgr) Singleton.getInstancia(this).music.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        inbackgr = false;
        Singleton.getInstancia(this).music.playBg();
        super.onResume();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return (keyCode == KeyEvent.KEYCODE_BACK ? true : super.onKeyDown(keyCode, event));
    }

    public void clickIf(final ImageView v) {
        v.animate()
                .rotation(10)
                .setDuration(100)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.animate()
                                .rotation(-10)
                                .setDuration(100)
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        v.animate()
                                                .rotation(0)
                                                .setDuration(100)
                                                .setListener(new Animator.AnimatorListener() {
                                                    @Override
                                                    public void onAnimationStart(Animator animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {


                                                    }

                                                    @Override
                                                    public void onAnimationCancel(Animator animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationRepeat(Animator animation) {

                                                    }
                                                });
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }
}
