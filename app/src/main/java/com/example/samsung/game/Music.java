package com.example.samsung.game;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Samsung on 06/09/2017.
 */

public  class Music
{

    MediaPlayer mediaPlayer;
    boolean isPlay = false;
    Context context;

    public Music(Context context, String sound)
    {
        this.context = context;
        int id_sound = context.getResources().getIdentifier(sound, "raw", context.getPackageName());
        mediaPlayer = MediaPlayer.create(context, id_sound);
        mediaPlayer.setLooping(true);

    }

    public void play()
    {
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
            isPlay = true;
        }

    }

    public void stop()
    {
        isPlay = false;
        mediaPlayer.pause();
    }
    public void pause()
    {
        isPlay = false;
        mediaPlayer.pause();

    }
    public void playBg() {
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
            isPlay = true;
        }
    }


    public void startMusic()
    {

        if (isPlay)
        {
            play();
        }
    }

    public void downVolume()
    {
        mediaPlayer.setVolume(0.2f,0.2f);
    }

    public void upVolume()
    {
        mediaPlayer.setVolume(1,1);
    }




}
