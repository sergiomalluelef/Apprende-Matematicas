package com.example.samsung.game;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;

public class Pantallainicio extends AppCompatActivity implements View.OnClickListener {
    ImageButton refrescar;
    ImageView nube1, nube2, nube3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallainicio);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ProgressBar progresCarga = (ProgressBar) findViewById(R.id.progressBar);

        refrescar = (ImageButton) findViewById(R.id.imageButton);
        refrescar.setOnClickListener(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progresCarga.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (conectado(getApplicationContext())) {
                            try {
                                if (descargaDatos()) {
                                    Intent intent = new Intent(Pantallainicio.this, MainGame.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.transicion_entrada,R.anim.transicion_salida);
                                    finish();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            alerta();
                        }
                    }

                }, 4000);
            }
        }, 1000);


        nube1 = (ImageView) findViewById(R.id.imgnube1);
        nube2 = (ImageView) findViewById(R.id.imgnube2);
        nube3 = (ImageView) findViewById(R.id.imgnube3);

        nube1.setX(2000);
        nube1.setY(500);

        nube2.setX(500);
        nube2.setY(110);

        nube3.setX(100);
        nube3.setY(700);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = nube1.getWidth();
                final float translationX = width * progress;
                nube3.setTranslationX(translationX);
                nube2.setTranslationX(translationX + width * 2);
                nube1.setTranslationX(translationX);
            }
        });
        animator.start();




    }

    public static boolean conectado(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public boolean descargaDatos() throws InterruptedException, IOException {
        String command = "ping -c 1 8.8.8.8";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

    @Override
    public void onBackPressed() {
        Intent refresh = new Intent(this, Pantallainicio.class);
        startActivity(refresh);
    }

    public void refrescar() {
        Intent refresh = new Intent(this, Pantallainicio.class);
        startActivity(refresh);
    }

    public void alerta() {
        AlertDialog.Builder myBulid = new AlertDialog.Builder(this);
        myBulid.setMessage("Es necesaria una conexión a internet, ¿Conectar?");
        myBulid.setTitle("Sin Conexión");
        myBulid.setPositiveButton("Red movil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                startActivity(i);
                refrescar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refrescar();
                    }
                }, 8000);
            }
        });

        myBulid.setNegativeButton("Wi-Fi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                wifi.setWifiEnabled(true);
                refrescar.setVisibility(View.VISIBLE);
            }
        });
        /*myBulid.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });*/
        myBulid.setNeutralButton("Seguir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Pantallainicio.this, MainGame.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = myBulid.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == refrescar) {
            refrescar();
        }
    }
}
