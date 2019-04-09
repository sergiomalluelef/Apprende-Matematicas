package com.example.samsung.game;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by Samsung on 06/09/2017.
 */

public class TextToSpeak extends Activity {

    Context context;
    TextToSpeech textToSpeech;

    public TextToSpeak(Context context, final String text)
    {
        super();
        this.context = context;

        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR)
                {
                    Locale loc = new Locale("spa", "MEX");//hablo español Mexico
                    //Locale loc = new Locale("es", "SPA");//hablo español coño
                    textToSpeech.setLanguage(loc);
                    //textToSpeech.setLanguage(Locale.ENGLISH);//hablo english
                    textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });
    }
    public void initTextToSpeech(String text)
    {

        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }
    public void deleteSpeech()
    {
        if (textToSpeech!= null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();

        }
    }

    public void onPause()
    {
        if (textToSpeech!= null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();

        }
        super.onPause();
    }


}
