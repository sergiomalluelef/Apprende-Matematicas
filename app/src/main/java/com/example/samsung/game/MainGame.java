package com.example.samsung.game;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static android.content.ContentValues.TAG;

public class MainGame extends Activity implements View.OnTouchListener {



    RelativeLayout principal;
    ImageView iconoplay, btnFacebook, iconoLogin, nub1,nub2,nub3, imgLogo;
    private boolean inbackgr = false;
    Sonido sonido = new Sonido(this);

    TextToSpeak textToSpeak;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        Singleton.getInstancia(this).music.play();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        principal = (RelativeLayout) findViewById(R.id.principal);
        btnFacebook = (ImageView) findViewById(R.id.btnFacebook);
        iconoLogin = (ImageView) findViewById(R.id.imageLogin);
        iconoLogin.setOnTouchListener(this);

        btnFacebook.setOnTouchListener(this);

        iconoplay = (ImageView) findViewById(R.id.iconoPlay);
        iconoplay.setOnTouchListener(this);
        iconoplay.startAnimation(animationFadeIn);

        imgLogo = (ImageView) findViewById(R.id.imageView);
        imgLogo.setOnTouchListener(this);

        nub1 = (ImageView) findViewById(R.id.imgn);
        nub2 = (ImageView) findViewById(R.id.imgn1);
        nub3 = (ImageView) findViewById(R.id.imgn2);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 6.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(100000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = nub1.getWidth();
                final float translationX = width * progress;
                nub1.setTranslationX(translationX);
                nub2.setTranslationX(translationX - width);
                nub3.setTranslationX(translationX);

            }
        });
        animator.start();



    }

    public void abrirFacebook() {

        String facebookId = "fb://page/410593528976177";
        String urlPage = "https://www.facebook.com/somosinvictos/";

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));
        } catch (Exception e) {
            Log.e(TAG, "Aplicación no instalada.");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
        }
    }

    public boolean onTouch(View v, MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (iconoplay == v) {
                sonido.playSound("click");
                pasaraotroIntent(this, MainMenus.class);
                overridePendingTransition(R.anim.transicion_entrada,R.anim.transicion_salida);
            }if (btnFacebook == v) {
                sonido.playSound("click");
                abrirFacebook();
            }
            if(imgLogo == v){

                Singleton.getInstancia(this).music.downVolume();
                if(i == 0){
                    sonido.playSound("bienvenido");
                    i++;
                }else if(i == 1){
                    sonido.playSound("hola_como_estas");
                    i++;
                }else if(i == 2){
                    sonido.playSound("vamos_a_aprender_matematicas");
                    i++;
                }else{
                    sonido.playSound("apreta_jugar_para_empezar");
                }


            }


        }
        return true;
    }

    public void pasaraotroIntent(Context context, Class activity) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        startActivity(intent);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return (keyCode == KeyEvent.KEYCODE_BACK ? true : super.onKeyDown(keyCode, event));
    }


}
