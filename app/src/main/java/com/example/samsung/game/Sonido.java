package com.example.samsung.game;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by Samsung on 05/09/2017.
 */

public class Sonido {

    private AudioAttributes tributes;
    private SoundPool soundPlayer = null;
    final int SOUND_POOL = 200;
    private Context context;
    private int id_play;

    public Sonido(Context context) {
        this.context = context;
    }


    public void playSound(String sound) {
        //Log.d("AudioSoundPool","Sonido: "+sound);
        if (sound != null && sound.length() > 0) {
            try {
                if (soundPlayer != null && id_play > 0) {
                    endSoundPool();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AudioAttributes atribute = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build();
                    soundPlayer = new SoundPool.Builder()
                            .setAudioAttributes(atribute)
                            .setMaxStreams(SOUND_POOL)
                            .build();
                    //Log.d("AudioSoundPool","SoundPool.Builder");
                } else {
                    soundPlayer = new SoundPool(200, AudioManager.STREAM_MUSIC, 0);
                }

                int id_sound = context.getResources().getIdentifier(sound, "raw", context.getPackageName());
                final int reproducirSound = soundPlayer.load(context, id_sound, 200);

                soundPlayer.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        //Log.d("AudioSoundPool","Cargo..");
                        play(reproducirSound);
                    }
                });
            } catch (Exception e) {
                //Log.d("AudioSoundPool","Error .."+e);
                e.printStackTrace();
            }
        } else {
            //Log.d("AudioSoundPool","NULO");
        }

    }

    public void play(int audio) {
        //Log.d("AudioSoundPool","Colocando Play "+audio);
        this.id_play = soundPlayer.play(audio, 1, 1, 0, 0, 1f);
        //Log.d("AudioSoundPool","Nuevo IDPlay "+this.id_play);
    }

    public void stop() {
        if (soundPlayer != null && id_play > 0) {
            soundPlayer.stop(this.id_play);
            //Log.d("AudioSoundPool","Stopeando...");
        }
        //Log.d("AudioSoundPool","--------------------------");
    }

    public void endSoundPool() {
        //Log.d("AudioSoundPool","Hacer Nulo");
        soundPlayer.stop(id_play);
        soundPlayer.unload(this.id_play);
        soundPlayer.release();
        soundPlayer = null;
    }

}
